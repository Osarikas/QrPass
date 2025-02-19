package ru.myitschool.work.domain.door.open

interface DoorRepo {
    suspend fun openDoor(code: String) : Result<Unit>
}