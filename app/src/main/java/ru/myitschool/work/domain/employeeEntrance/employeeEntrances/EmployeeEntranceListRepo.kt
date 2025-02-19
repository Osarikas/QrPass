package ru.myitschool.work.domain.employeeEntrance.employeeEntrances

import ru.myitschool.work.domain.entities.EmployeeEntranceEntity

interface EmployeeEntranceListRepo {
    suspend fun getList(pageNum : Int, pageSize: Int) : Result<List<EmployeeEntranceEntity>>
}