package ru.myitschool.work.domain.door

import ru.myitschool.work.domain.entities.OpenEntity

interface DoorRepo {
    suspend fun openDoor(openEntity: OpenEntity) : Result<Unit>
}