package com.mycomp.githubrepositories.data.dataservice.repository

import android.database.sqlite.SQLiteException
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.data.dataservice.apiservice.GithubService
import com.mycomp.githubrepositories.data.dataservice.paging.GithubRepositoriesPagingSource
import com.mycomp.githubrepositories.data.dataservice.sqlservice.GithubRepositoriesDatabase
import com.mycomp.githubrepositories.data.mappers.GithubReposToDomainFromEntityMapper
import com.mycomp.githubrepositories.data.mappers.GithubReposToDomainFromResponseMapper
import com.mycomp.githubrepositories.data.mappers.GithubReposToEntityFromDomainMapper
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GithubReposRepositoryImpl(
    private val githubService: GithubService,
    private val toDomainMapper: GithubReposToDomainFromResponseMapper,
    private val toEntityMapper: GithubReposToEntityFromDomainMapper,
    private val toDomainFromEntityMapper: GithubReposToDomainFromEntityMapper,
    private val db: GithubRepositoriesDatabase
) : GithubReposRepository {

    override fun getRepositoriesByUserName(userName: String): Flow<PagingData<RepositoryModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GithubRepositoriesPagingSource(
                    githubService = githubService,
                    owner = userName,
                )
            }
        ).flow.map {
            it.map {
                toDomainMapper.map(it)
            }
        }

    override suspend fun addDownloadedRepo(repositoryModel: RepositoryModel) {
        db.githubRepositoryDao().insertGithubDownloadedRepo(

            toEntityMapper.map(repositoryModel)
        )
    }

    override suspend fun getDownloadedRepos(): Flow<Result<List<RepositoryModel>>> = flow {
        emit(Result.Loading())
        try {
            val repos = db.githubRepositoryDao().getDownloadedRepos()
            emit(Result.Success(repos.map { repositoryEntity ->
                toDomainFromEntityMapper.map(repositoryEntity)
            }))
        } catch (e: SQLiteException) {
            emit(
                Result.Error(
                    message = "Something went wrong",
                    data = emptyList()
                )
            )
        }
    }
}