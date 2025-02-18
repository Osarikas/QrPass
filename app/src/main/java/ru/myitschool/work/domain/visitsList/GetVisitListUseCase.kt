package ru.myitschool.work.domain.visitsList

class GetVisitListUseCase(
    private val repo: VisitListRepo
) {
    suspend operator fun invoke(pageNum : Int, pageSize: Int) = repo.getList(pageNum, pageSize)
}