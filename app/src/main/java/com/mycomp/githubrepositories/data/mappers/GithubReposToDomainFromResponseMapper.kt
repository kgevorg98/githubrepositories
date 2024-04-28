package com.mycomp.githubrepositories.data.mappers

import com.mycomp.githubrepositories.core.Mapper
import com.mycomp.githubrepositories.data.models.remote.RepositoryResponse
import com.mycomp.githubrepositories.domain.models.RepositoryModel

class GithubReposToDomainFromResponseMapper : Mapper<RepositoryResponse, RepositoryModel> {
    override fun map(targetModel: RepositoryResponse): RepositoryModel =
        RepositoryModel(
            id = targetModel.id ?: -1,
            name = targetModel.name ?: "",
            fullName = targetModel.fullName ?: "",
            htmlUrl = targetModel.htmlUrl ?: "",
        )
}