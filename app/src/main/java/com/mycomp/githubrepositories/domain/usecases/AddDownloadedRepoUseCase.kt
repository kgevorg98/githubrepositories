package com.mycomp.githubrepositories.domain.usecases

import com.mycomp.githubrepositories.domain.interactors.AddDownloadedRepoInteractor
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository

class AddDownloadedRepoUseCase(
   private val githubReposRepository: GithubReposRepository
):AddDownloadedRepoInteractor{
    override suspend fun invoke(repositoryModel: RepositoryModel) {
        githubReposRepository.addDownloadedRepo(repositoryModel)
    }
}