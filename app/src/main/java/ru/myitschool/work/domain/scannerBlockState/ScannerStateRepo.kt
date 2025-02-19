package ru.myitschool.work.domain.scannerBlockState

interface ScannerStateRepo {
    suspend fun changeState(state: String, login: String) : Result<Unit>
}