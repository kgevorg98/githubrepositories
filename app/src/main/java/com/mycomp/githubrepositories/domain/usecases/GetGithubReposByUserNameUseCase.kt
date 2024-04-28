package com.mycomp.githubrepositories.domain.usecases

import androidx.paging.PagingData
import com.mycomp.githubrepositories.domain.interactors.GetGithubReposByUserNameInteractor
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository
import kotlinx.coroutines.flow.Flow

class GetGithubReposByUserNameUseCase(
    private val githubReposRepository: GithubReposRepository
) : GetGithubReposByUserNameInteractor {
    override fun invoke(userName: String): Flow<PagingData<RepositoryModel>> {
        return githubReposRepository.getRepositoriesByUserName(userName)
    }
}