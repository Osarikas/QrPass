package ru.myitschool.work.data.scannerState

import ru.myitschool.work.domain.scannerBlockState.ScannerStateRepo

class ScannerStateRepoImpl(
    private val networkDataSource: ScannerStateNetworkDataSource
) : ScannerStateRepo {
    override suspend fun changeState(state: String, login: String) : Result<Unit> {
        return networkDataSource.setScannerState(state, login)
    }
}