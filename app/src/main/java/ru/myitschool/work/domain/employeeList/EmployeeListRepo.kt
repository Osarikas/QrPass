package ru.myitschool.work.domain.employeeList

import ru.myitschool.work.entities.EmployeeEntity

interface EmployeeListRepo {
    suspend fun getList() : Result<List<EmployeeEntity>>
}