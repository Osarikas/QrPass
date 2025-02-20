package ru.myitschool.work.domain.scannerBlockState

interface ScannerStateRepo {
    suspend fun changeState(login: String) : Result<Unit>
}