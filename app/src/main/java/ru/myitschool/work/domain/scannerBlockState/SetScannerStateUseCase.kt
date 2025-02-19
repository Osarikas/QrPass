package ru.myitschool.work.domain.scannerBlockState

class SetScannerStateUseCase(
    private val repo: ScannerStateRepo
) {
    suspend operator fun invoke(state: String, login: String) = repo.changeState(state, login)
}