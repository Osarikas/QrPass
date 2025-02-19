package ru.myitschool.work.data.deleteEmployee

import ru.myitschool.work.domain.deleteEmployee.DeleteEmployeeRepo

class DeleteEmployeeRepoImpl(
    private val networkDataSource: DeleteEmployeeNetworkDataSource
) : DeleteEmployeeRepo {
    override suspend fun deleteEmployee(login: String): Result<Unit> {
        return networkDataSource.deleteEmployee(login)
    }
}