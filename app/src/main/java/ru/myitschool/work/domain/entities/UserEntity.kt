package ru.myitschool.work.domain.entities

data class UserEntity(
    val id: Long,
    val login: String,
    val name: String,
    val authority: String,
    val photoUrl: String?,
    val position: String
)