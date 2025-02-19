package ru.myitschool.work.data.door.open

import ru.myitschool.work.domain.door.open.DoorRepo

class DoorRepoImpl(
   private val networkDataSource: DoorNetworkDataSource
) : DoorRepo {
    override suspend fun openDoor(code: String): Result<Unit> {
        return networkDataSource.openDoor(code)
    }
}