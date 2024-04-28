package com.mycomp.githubrepositories.domain.models

data class RepositoryModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
)