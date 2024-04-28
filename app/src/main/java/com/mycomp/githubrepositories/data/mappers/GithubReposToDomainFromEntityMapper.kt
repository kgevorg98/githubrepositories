package com.mycomp.githubrepositories.data.mappers

import com.mycomp.githubrepositories.core.Mapper
import com.mycomp.githubrepositories.data.models.db.RepositoryEntity
import com.mycomp.githubrepositories.domain.models.RepositoryModel

class GithubReposToDomainFromEntityMapper : Mapper<RepositoryEntity, RepositoryModel> {
    override fun map(targetModel: RepositoryEntity): RepositoryModel =
        RepositoryModel(
            id = targetModel.id,
            name = targetModel.name,
            fullName = targetModel.fullName,
            htmlUrl = targetModel.htmlUrl
        )
}