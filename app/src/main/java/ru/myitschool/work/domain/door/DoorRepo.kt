package ru.myitschool.work.domain.door

interface DoorRepo {
    suspend fun openDoor(code: String) : Result<Unit>
}