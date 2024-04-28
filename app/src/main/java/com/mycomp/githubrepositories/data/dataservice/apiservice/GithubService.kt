package com.mycomp.githubrepositories.data.dataservice.apiservice

import com.mycomp.githubrepositories.data.models.remote.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {
    companion object {
        const val REPOSITORIES = "users/{owner}/repos"
    }

    @GET(REPOSITORIES)
    suspend fun getRepositoriesByUserName(
        @Path("owner") owner: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): Response<List<RepositoryResponse>>
}