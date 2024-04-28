package com.mycomp.githubrepositories.domain.usecases

import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.domain.interactors.GetDownloadedRepositoriesInteractor
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow

class GetDownloadedRepositoriesUseCase(
    private val githubReposRepository: GithubReposRepository
) : GetDownloadedRepositoriesInteractor {
    override suspend fun invoke(): Flow<Result<List<RepositoryModel>>> {
        return githubReposRepository.getDownloadedRepos()
    }
}