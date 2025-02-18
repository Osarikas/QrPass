package ru.myitschool.work.domain.visitsList

import ru.myitschool.work.domain.entities.UserEntity
import ru.myitschool.work.domain.entities.VisitEntity

interface VisitListRepo {
    suspend fun getList(pageNum : Int, pageSize: Int) : Result<List<VisitEntity>>
}