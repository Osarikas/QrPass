package ru.myitschool.work.domain.employeeEntrance.allEntrances

import ru.myitschool.work.domain.entities.EmployeeEntranceEntity

interface AllEntranceListRepo {
    suspend fun getList(pageNum : Int, pageSize: Int) : Result<List<EmployeeEntranceEntity>>
}