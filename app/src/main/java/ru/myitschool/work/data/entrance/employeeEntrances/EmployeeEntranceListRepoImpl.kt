package ru.myitschool.work.data.entrance.employeeEntrances

import ru.myitschool.work.entities.EmployeeEntranceEntity
import ru.myitschool.work.domain.employeeEntrance.employeeEntrances.EmployeeEntranceListRepo

class EmployeeEntranceListRepoImpl(
    private val networkDataSource: EmployeeEntranceListNetworkDataSource
) : EmployeeEntranceListRepo {
    override suspend fun getList(pageNum: Int, pageSize: Int): Result<List<EmployeeEntranceEntity>> {
        return networkDataSource.getList(pageNum, pageSize).map { pagingDTO ->
            pagingDTO.content?.mapNotNull { dto->
                EmployeeEntranceEntity(
                    id = dto.id ?: return@mapNotNull null,
                    scanTime = dto.scanTime ?: return@mapNotNull null,
                    readerName = dto.readerName ?: return@mapNotNull null,
                    type = dto.type ?: return@mapNotNull null,
                    entryType = dto.entryType ?: return@mapNotNull null
                )
            }?: return Result.failure(IllegalStateException("List parse error"))
        }
    }
}