package com.mycomp.githubrepositories.domain.interactors

import androidx.paging.PagingData
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface GetGithubReposByUserNameInteractor {
    operator fun invoke(userName: String): Flow<PagingData<RepositoryModel>>
}