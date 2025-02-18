package ru.myitschool.work.domain.info

import ru.myitschool.work.domain.entities.UserEntity

interface InfoRepo {
    suspend fun getInfo(): Result<UserEntity>
}