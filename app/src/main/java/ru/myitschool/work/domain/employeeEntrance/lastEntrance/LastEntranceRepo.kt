package ru.myitschool.work.domain.employeeEntrance.lastEntrance

import ru.myitschool.work.entities.EmployeeEntranceEntity

interface LastEntranceRepo {
    suspend fun getLastEntrance() : Result<EmployeeEntranceEntity>
}