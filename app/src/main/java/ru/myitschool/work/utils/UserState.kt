package ru.myitschool.work.utils

import ru.myitschool.work.domain.entities.UserEntity

sealed class UserState {
    object Loading : UserState()
    data class Success(val userEntity: UserEntity) : UserState()
    object Error : UserState()
}