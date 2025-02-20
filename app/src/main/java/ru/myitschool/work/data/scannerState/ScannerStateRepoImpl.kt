package ru.myitschool.work.data.scannerState

import ru.myitschool.work.domain.scannerBlockState.ScannerStateRepo

class ScannerStateRepoImpl(
    private val networkDataSource: ScannerStateNetworkDataSource
) : ScannerStateRepo {
    override suspend fun changeState(login: String) : Result<Unit> {
        return networkDataSource.setScannerState(login)
    }
}