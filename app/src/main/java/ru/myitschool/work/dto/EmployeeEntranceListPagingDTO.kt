package ru.myitschool.work.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeEntranceListPagingDTO(
    @SerialName("content") val content : List<EmployeeEntranceDTO>?
)
