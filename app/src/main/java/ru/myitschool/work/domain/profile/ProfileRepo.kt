package ru.myitschool.work.domain.profile

import ru.myitschool.work.entities.EmployeeEntity

interface ProfileRepo {
    suspend fun getInfo(): Result<EmployeeEntity>
}