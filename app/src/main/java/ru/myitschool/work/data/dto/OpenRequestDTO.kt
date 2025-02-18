package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenRequestDTO(
   @SerialName("value") val value: Long
)