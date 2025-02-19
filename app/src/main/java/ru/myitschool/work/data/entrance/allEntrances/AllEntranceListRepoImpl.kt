package ru.myitschool.work.data.entrance.allEntrances

import ru.myitschool.work.domain.employeeEntrance.allEntrances.AllEntranceListRepo
import ru.myitschool.work.entities.EmployeeEntranceEntity

class AllEntranceListRepoImpl(
    private val networkDataSource: AllEntranceListNetworkDataSource
) : AllEntranceListRepo {
    override suspend fun getList(
        pageNum: Int,
        pageSize: Int
    ): Result<List<EmployeeEntranceEntity>> {
        return networkDataSource.getList(pageNum, pageSize).map { pagingDTO ->
            pagingDTO.content?.mapNotNull { dto ->
                EmployeeEntranceEntity(
                    id = dto.id ?: return@mapNotNull null,
                    scanTime = dto.scanTime ?: return@mapNotNull null,
                    readerName = dto.readerName ?: return@mapNotNull null,
                    type = dto.type ?: return@mapNotNull null,
                    entryType = dto.entryType ?: return@mapNotNull null,
                )
            }?: return Result.failure(IllegalStateException("List parse error"))
        }
    }
}