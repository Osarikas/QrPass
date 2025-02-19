package ru.myitschool.work.domain.employeeEntrance.employeeEntrances

class GetEmployeeEntranceListUseCase(
    private val repo: EmployeeEntranceListRepo
) {
    suspend operator fun invoke(pageNum : Int, pageSize: Int) = repo.getList(pageNum, pageSize)
}