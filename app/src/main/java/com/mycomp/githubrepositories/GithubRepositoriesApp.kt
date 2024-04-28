package com.mycomp.githubrepositories

import android.app.Application
import android.content.Context
import com.mycomp.githubrepositories.data.di.apiModule
import com.mycomp.githubrepositories.data.di.databaseModule
import com.mycomp.githubrepositories.data.di.repositoryModule
import com.mycomp.githubrepositories.domain.di.interactorsModule
import com.mycomp.githubrepositories.presentation.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GithubRepositoriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modules)
            androidLogger()
            androidContext(this@GithubRepositoriesApp)
        }
    }

    private val modules = listOf(
        apiModule,
        databaseModule,
        repositoryModule,
        interactorsModule,
        viewModelsModule
    )
}