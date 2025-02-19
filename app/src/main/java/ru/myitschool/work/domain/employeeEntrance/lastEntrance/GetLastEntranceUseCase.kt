package ru.myitschool.work.domain.employeeEntrance.lastEntrance

class GetLastEntranceUseCase(
    private val repo : LastEntranceRepo
) {
    suspend operator fun invoke() = repo.getLastEntrance()
}