package ru.myitschool.work.data.visitsList.employeeEntrances

import ru.myitschool.work.domain.entities.EmployeeEntranceEntity
import ru.myitschool.work.domain.visitsList.EmployeeEntranceListRepo

class EmployeeEntranceListRepoImpl(
    private val networkDataSource: EmployeeEntranceListNetworkDataSource
) : EmployeeEntranceListRepo {
    override suspend fun getList(pageNum: Int, pageSize: Int): Result<List<EmployeeEntranceEntity>> {
        return networkDataSource.getList(pageNum, pageSize).map { pagingDTO ->
            pagingDTO.content?.mapNotNull { dto->
                EmployeeEntranceEntity(
                    scanTime = dto.scanTime ?: return@mapNotNull null,
                    readerName = dto.readerName ?: return@mapNotNull null,
                    type = dto.type ?: return@mapNotNull null
                )
            }?: return Result.failure(IllegalStateException("List parse error"))
        }
    }
}