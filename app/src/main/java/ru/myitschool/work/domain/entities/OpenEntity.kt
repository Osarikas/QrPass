package ru.myitschool.work.domain.entities

import ru.myitschool.work.data.dto.OpenRequestDTO

data class OpenEntity(
    val value: Long
){
    fun toDto() : OpenRequestDTO{
        return OpenRequestDTO(
            value = value
        )
    }
}