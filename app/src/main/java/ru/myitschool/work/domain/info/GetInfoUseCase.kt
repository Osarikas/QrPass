package ru.myitschool.work.domain.info

class GetInfoUseCase(
    private val repo: InfoRepo
) {
    suspend operator fun invoke() = repo.getInfo()
}