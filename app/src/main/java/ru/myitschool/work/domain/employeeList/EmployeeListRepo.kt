package ru.myitschool.work.domain.employeeList

import ru.myitschool.work.entities.EmployeeEntity

interface EmployeeListRepo {
    suspend fun getList(
        pageNum: Int,
        pageSize: Int ) : Result<List<EmployeeEntity>>
}