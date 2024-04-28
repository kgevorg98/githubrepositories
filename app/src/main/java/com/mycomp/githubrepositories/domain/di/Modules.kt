package com.mycomp.githubrepositories.domain.di

import com.mycomp.githubrepositories.domain.interactors.AddDownloadedRepoInteractor
import com.mycomp.githubrepositories.domain.interactors.GetDownloadedRepositoriesInteractor
import com.mycomp.githubrepositories.domain.interactors.GetGithubReposByUserNameInteractor
import com.mycomp.githubrepositories.domain.usecases.AddDownloadedRepoUseCase
import com.mycomp.githubrepositories.domain.usecases.GetDownloadedRepositoriesUseCase
import com.mycomp.githubrepositories.domain.usecases.GetGithubReposByUserNameUseCase
import org.koin.dsl.module


    val interactorsModule = module {
        factory<GetGithubReposByUserNameInteractor> { GetGithubReposByUserNameUseCase(get()) }
        factory<AddDownloadedRepoInteractor> { AddDownloadedRepoUseCase(get()) }
        factory<GetDownloadedRepositoriesInteractor> { GetDownloadedRepositoriesUseCase(get()) }
    }
