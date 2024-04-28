package com.mycomp.githubrepositories.domain.interactors

import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface GetDownloadedRepositoriesInteractor {
    suspend operator fun invoke(): Flow<Result<List<RepositoryModel>>>
}