package ru.myitschool.work.domain.deleteEmployee

interface DeleteEmployeeRepo {
    suspend fun deleteEmployee(login : String): Result<Unit>
}