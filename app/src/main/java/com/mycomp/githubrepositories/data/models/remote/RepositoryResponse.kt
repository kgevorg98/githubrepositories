package com.mycomp.githubrepositories.data.models.remote

import com.google.gson.annotations.SerializedName


data class RepositoryResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("html_url")
    val htmlUrl: String?
)