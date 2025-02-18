package ru.myitschool.work.data.info

import ru.myitschool.work.domain.entities.UserEntity
import ru.myitschool.work.domain.info.InfoRepo

class InfoRepoImpl(
    private val networkDataSource: InfoNetworkDataSource
): InfoRepo {
    override suspend fun getInfo(): Result<UserEntity> {
        return networkDataSource.getInfo().map { dto ->
            UserEntity(
                name = dto.name,
                position = dto.position,
                lastVisit = dto.lastVisit,
                photo = dto.photo
            )
        }
    }
}