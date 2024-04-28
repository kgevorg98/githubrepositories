package com.mycomp.githubrepositories.data.dataservice.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mycomp.githubrepositories.data.dataservice.apiservice.GithubService
import com.mycomp.githubrepositories.data.models.remote.RepositoryResponse


class GithubRepositoriesPagingSource(
    private val githubService: GithubService,
    private val owner: String,
) : PagingSource<Int, RepositoryResponse>() {
    override fun getRefreshKey(state: PagingState<Int, RepositoryResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryResponse> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response =
                githubService.getRepositoriesByUserName(owner, page = page, perPage = limit).body()
                    ?: emptyList()
            val nextKey = if (response.isEmpty()) null else page + 1
            val prevKey = if (page > 1) page - 1 else null
            LoadResult.Page(
                data = response,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}