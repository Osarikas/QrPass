package ru.myitschool.work.domain.door.open

class OpenDoorUseCase(
   private val repo: DoorRepo
) {
    suspend operator fun invoke(code: String) = repo.openDoor(code)
}