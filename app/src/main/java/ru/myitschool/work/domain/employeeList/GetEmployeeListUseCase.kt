package ru.myitschool.work.domain.employeeList

class GetEmployeeListUseCase(
    private val repo: EmployeeListRepo
) {
    suspend operator fun invoke(pageNum: Int, pageSize: Int) = repo.getList(pageNum, pageSize)
}