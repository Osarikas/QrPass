package ru.myitschool.work.data.employeeList

import ru.myitschool.work.domain.employeeList.EmployeeListRepo
import ru.myitschool.work.entities.EmployeeEntity

class EmployeeListRepoImpl : EmployeeListRepo {
    override suspend fun getList(): Result<List<EmployeeEntity>> {
        TODO("Not yet implemented")
    }
}