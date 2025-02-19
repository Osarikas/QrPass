package ru.myitschool.work.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeDTO(
    @SerialName("id") val id: Long?,
    @SerialName("login") val login: String?,
    @SerialName("name") val name: String?,
    @SerialName("authority") val authority : String?,
    @SerialName("photoUrl") val photoUrl: String?,
    @SerialName("position") val position: String?,
    @SerialName("qrEnabled") val qrEnabled: Boolean?
)

