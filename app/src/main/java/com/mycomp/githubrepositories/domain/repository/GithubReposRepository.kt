package com.mycomp.githubrepositories.domain.repository

import androidx.paging.PagingData
import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface GithubReposRepository {
    fun getRepositoriesByUserName(userName: String):  Flow<PagingData<RepositoryModel>>
    suspend fun addDownloadedRepo(repositoryModel: RepositoryModel): Unit
    suspend fun getDownloadedRepos(): Flow<Result<List<RepositoryModel>>>
}