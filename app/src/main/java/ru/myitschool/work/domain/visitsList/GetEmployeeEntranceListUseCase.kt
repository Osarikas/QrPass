package ru.myitschool.work.domain.visitsList

class GetEmployeeEntranceListUseCase(
    private val repo: EmployeeEntranceListRepo
) {
    suspend operator fun invoke(pageNum : Int, pageSize: Int) = repo.getList(pageNum, pageSize)
}