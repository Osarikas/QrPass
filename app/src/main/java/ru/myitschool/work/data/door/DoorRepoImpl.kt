package ru.myitschool.work.data.door

import ru.myitschool.work.domain.door.DoorRepo

class DoorRepoImpl(
   private val networkDataSource: DoorNetworkDataSource
) : DoorRepo {
    override suspend fun openDoor(code: String): Result<Unit> {
        return networkDataSource.openDoor(code)
    }
}