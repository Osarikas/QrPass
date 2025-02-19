package ru.myitschool.work.ui.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.myitschool.work.entities.EmployeeEntranceEntity

class EmployeeEntranceListPagingSource(
    private val request: suspend(pageNum: Int, pageSize: Int) ->Result<List<EmployeeEntranceEntity>>
) : PagingSource<Int, EmployeeEntranceEntity>() {
    override fun getRefreshKey(state: PagingState<Int, EmployeeEntranceEntity>): Int? {
        return state.anchorPosition?.let{
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EmployeeEntranceEntity> {
        val pageNum = params.key ?: 0
        return request.invoke(
            pageNum, params.loadSize
        ).fold(
            onSuccess = { value ->
                LoadResult.Page(
                    data = value,
                    prevKey = (pageNum - 1).takeIf { it >= 0 },
                    nextKey = (pageNum + 1).takeIf { value.size == params.loadSize }
                )

            },
            onFailure = { e->
                println(e)
                LoadResult.Error(e)
            }
        )
    }
}