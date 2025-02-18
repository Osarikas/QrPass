package ru.myitschool.work.domain.door

import ru.myitschool.work.domain.entities.OpenEntity

class OpenDoorUseCase(
   private val repo: DoorRepo
) {
    suspend operator fun invoke(openEntity: OpenEntity) = repo.openDoor(
        openEntity = openEntity
    )
}