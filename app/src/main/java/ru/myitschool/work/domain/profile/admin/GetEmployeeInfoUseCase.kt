package ru.myitschool.work.domain.profile.admin

class GetEmployeeInfoUseCase(
    private val repo : EmployeeInfoRepo
) {
    suspend operator fun invoke(login : String) = repo.getInfo(login)
}