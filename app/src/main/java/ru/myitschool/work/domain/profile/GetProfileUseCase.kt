package ru.myitschool.work.domain.profile

class GetProfileUseCase(
    private val repo: ProfileRepo
) {
    suspend operator fun invoke() = repo.getInfo()
}