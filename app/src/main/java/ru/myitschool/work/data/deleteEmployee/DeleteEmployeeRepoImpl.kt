package ru.myitschool.work.data.deleteEmployee

import ru.myitschool.work.domain.deleteEmployee.DeleteEmployeeRepo

class DeleteEmployeeRepoImpl : DeleteEmployeeRepo {
    override suspend fun deleteEmployee(login: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}