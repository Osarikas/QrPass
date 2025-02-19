package ru.myitschool.work.domain.profile.admin

class GetEmployeeProfileUseCase(
    private val repo : EmployeeProfileRepo
) {
    suspend operator fun invoke(login : String) = repo.getInfo(login)
}