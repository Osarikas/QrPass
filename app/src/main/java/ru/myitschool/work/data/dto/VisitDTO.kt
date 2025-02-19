package ru.myitschool.work.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.myitschool.work.utils.DateSerializer
import java.util.Date

@Serializable
data class VisitDTO(
    @SerialName("scanTime") @Serializable(with = DateSerializer::class) val scanTime : Date?,
    @SerialName("readerId") val readerName: String?,
    @SerialName("type") val type: String?
)
