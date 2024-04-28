package com.mycomp.githubrepositories.domain.interactors

import com.mycomp.githubrepositories.domain.models.RepositoryModel

interface AddDownloadedRepoInteractor {
   suspend operator fun invoke(repositoryModel: RepositoryModel)
}