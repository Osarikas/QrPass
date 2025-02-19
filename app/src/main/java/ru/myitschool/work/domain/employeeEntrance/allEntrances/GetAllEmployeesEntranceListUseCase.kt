package ru.myitschool.work.domain.employeeEntrance.allEntrances

class GetAllEmployeesEntranceListUseCase(
    private val repo: AllEntranceListRepo
) {
    suspend operator fun invoke(pageNum : Int, pageSize: Int) = repo.getList(pageNum, pageSize)
}