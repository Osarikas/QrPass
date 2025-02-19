package ru.myitschool.work.domain.employeeEntrance.allEntrances

import ru.myitschool.work.entities.EmployeeEntranceEntity

interface AllEntranceListRepo {
    suspend fun getList(pageNum : Int, pageSize: Int) : Result<List<EmployeeEntranceEntity>>
}