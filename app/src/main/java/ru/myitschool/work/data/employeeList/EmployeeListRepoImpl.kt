package ru.myitschool.work.data.employeeList

import ru.myitschool.work.domain.employeeList.EmployeeListRepo
import ru.myitschool.work.entities.EmployeeEntity

class EmployeeListRepoImpl(
    private val networkDataSource: EmployeeListNetworkDataSource
) : EmployeeListRepo {
    override suspend fun getList(
        pageNum: Int,
        pageSize: Int
    ): Result<List<EmployeeEntity>> {
        return networkDataSource.getList(pageNum, pageSize ).map { pagingDTO->
            pagingDTO.content?.mapNotNull { dto ->
                EmployeeEntity(
                    id = dto.id ?: return@mapNotNull null,
                    login = dto.login ?: return@mapNotNull null ,
                    name = dto.name ?: return@mapNotNull null,
                    authority = dto.authority ?: return@mapNotNull null,
                    photoUrl = dto.photoUrl,
                    position = dto.position ?: return@mapNotNull null,
                    qrEnabled = dto.qrEnabled ?: false
                )
            } ?: return Result.failure(IllegalStateException("List parse error"))
            }
        }
    }
