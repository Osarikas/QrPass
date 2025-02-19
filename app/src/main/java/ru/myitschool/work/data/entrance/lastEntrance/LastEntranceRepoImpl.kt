package ru.myitschool.work.data.entrance.lastEntrance

import ru.myitschool.work.domain.employeeEntrance.lastEntrance.LastEntranceRepo
import ru.myitschool.work.entities.EmployeeEntranceEntity

class LastEntranceRepoImpl(
    private val networkDataSource: LastEntranceNetworkDataSource
) : LastEntranceRepo {
    override suspend fun getLastEntrance(): Result<EmployeeEntranceEntity> {
        return networkDataSource.getLastEntrance().map { dto ->
            EmployeeEntranceEntity(
                id = dto.id ?: 0,
                scanTime = dto.scanTime,
                readerName = dto.readerName ?: "",
                type = dto.type ?: "",
                entryType = dto.entryType
            )
        }
    }
}