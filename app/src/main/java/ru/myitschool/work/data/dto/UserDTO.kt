package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id") val id: Int,
    @SerialName("login") val login: String,
    @SerialName("name") val name: String,
    @SerialName("photo") val photo: String,
    @SerialName("position") val position: String,
    @SerialName("lastVisit") val lastVisit: String
)

