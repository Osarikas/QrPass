package ru.myitschool.work.ui.admin.usersList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.myitschool.work.entities.EmployeeEntity

class EmployeeListPagingSource(
    private val request: suspend(pageNum: Int, pageSize: Int) ->Result<List<EmployeeEntity>>
) : PagingSource<Int, EmployeeEntity>() {
    override fun getRefreshKey(state: PagingState<Int, EmployeeEntity>): Int? {
        return state.anchorPosition?.let{
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EmployeeEntity> {
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