package ru.myitschool.work.data.visitsList

import ru.myitschool.work.domain.entities.UserEntity
import ru.myitschool.work.domain.entities.VisitEntity
import ru.myitschool.work.domain.visitsList.VisitListRepo

class VisitListRepoImpl(
    private val networkDataSource: VisitListNetworkDataSource
) : VisitListRepo {
    override suspend fun getList(pageNum: Int, pageSize: Int): Result<List<VisitEntity>> {
        return networkDataSource.getList(pageNum, pageSize).map { pagingDTO ->
            pagingDTO.content?.mapNotNull { dto->
                VisitEntity(
                    scanTime = dto.scanTime ?: return@mapNotNull null,
                    readerId = dto.readerId ?: return@mapNotNull null,
                    type = dto.type ?: return@mapNotNull null
                )
            }?: return Result.failure(IllegalStateException("List parse error"))
        }
    }
}