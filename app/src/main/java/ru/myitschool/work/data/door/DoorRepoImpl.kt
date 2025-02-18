package ru.myitschool.work.data.door

import ru.myitschool.work.domain.door.DoorRepo
import ru.myitschool.work.domain.entities.OpenEntity

class DoorRepoImpl(
   private val networkDataSource: DoorNetworkDataSource
) : DoorRepo {
    override suspend fun openDoor(openEntity: OpenEntity): Result<Unit> {
        return networkDataSource.openDoor(openEntity.toDto())
    }
}