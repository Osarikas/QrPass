package ru.myitschool.work.ui.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.myitschool.work.domain.entities.VisitEntity

class VisitListPagingSource(
    private val request: suspend(pageNum: Int, pageSize: Int) ->Result<List<VisitEntity>>
) : PagingSource<Int, VisitEntity>() {
    override fun getRefreshKey(state: PagingState<Int, VisitEntity>): Int? {
        return state.anchorPosition?.let{
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VisitEntity> {
        TODO("Not yet implemented")
    }
}