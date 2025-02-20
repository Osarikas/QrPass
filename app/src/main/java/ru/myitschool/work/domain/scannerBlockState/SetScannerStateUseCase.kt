package ru.myitschool.work.domain.scannerBlockState

class SetScannerStateUseCase(
    private val repo: ScannerStateRepo
) {
    suspend operator fun invoke(login: String) = repo.changeState(login)
}