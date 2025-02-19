package ru.myitschool.work.dto

import kotlinx.serialization.SerialName

data class EmployeePagingDTO (
    @SerialName("content") val content : List<EmployeeDTO>?
)