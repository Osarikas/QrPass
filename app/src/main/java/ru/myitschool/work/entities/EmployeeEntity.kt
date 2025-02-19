package ru.myitschool.work.entities

data class EmployeeEntity(
    val id: Long,
    val login: String,
    val name: String,
    val authority: String,
    val photoUrl: String?,
    val position: String,
    val qrEnabled: Boolean
)