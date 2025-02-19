package ru.myitschool.work.domain.deleteEmployee

class DeleteEmployeeUseCase(
    private val repo: DeleteEmployeeRepo
) {
    suspend operator fun invoke(login : String) = repo.deleteEmployee(login)
}