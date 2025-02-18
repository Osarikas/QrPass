package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VisitListPagingDTO(
    @SerialName("content") val content : List<VisitDTO>?
)
