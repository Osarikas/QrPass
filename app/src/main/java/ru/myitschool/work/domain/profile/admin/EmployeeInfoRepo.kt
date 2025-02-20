package ru.myitschool.work.domain.profile.admin

import ru.myitschool.work.entities.EmployeeEntity

interface EmployeeInfoRepo {
    suspend fun getInfo(login : String): Result<EmployeeEntity>
}