package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    @SerialName("id") val id: Long?,
    @SerialName("login") val login: String?,
    @SerialName("name") val name: String?,
    @SerialName("authority") val authority : String?,
    @SerialName("photoUrl") val photoUrl: String?,
    @SerialName("position") val position: String?
)

