package ru.myitschool.work.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.myitschool.work.utils.DateSerializer
import java.util.Date

@Serializable
data class EmployeeEntranceDTO(
    @SerialName("id") val id : Int?,
    @SerialName("entryTime") @Serializable(with = DateSerializer::class) val scanTime : Date?,
    @SerialName("readerName") val readerName: String?,
    @SerialName("type") val type: String?,
    @SerialName("entryType") val entryType: String?
)
