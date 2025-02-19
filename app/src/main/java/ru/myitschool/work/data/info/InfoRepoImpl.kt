package ru.myitschool.work.data.info

import ru.myitschool.work.domain.entities.UserEntity
import ru.myitschool.work.domain.info.InfoRepo

class InfoRepoImpl(
    private val networkDataSource: InfoNetworkDataSource
): InfoRepo {
    override suspend fun getInfo(): Result<UserEntity> {
        return networkDataSource.getInfo().map { dto->
            UserEntity(
                id = dto.id ?: 0,
                login = dto.login ?: "",
                name = dto.login ?: "",
                authority = dto.authority ?: "",
                photoUrl = dto.photoUrl,
                position = dto.position ?: ""
            )
        }

    }
}