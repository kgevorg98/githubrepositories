package com.mycomp.githubrepositories.data.di

import android.app.Application
import androidx.room.Room
import com.mycomp.githubrepositories.data.utils.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import com.mycomp.githubrepositories.BuildConfig
import com.mycomp.githubrepositories.data.dataservice.apiservice.GithubService
import com.mycomp.githubrepositories.data.dataservice.repository.GithubReposRepositoryImpl
import com.mycomp.githubrepositories.data.dataservice.sqlservice.GithubRepositoriesDatabase
import com.mycomp.githubrepositories.data.mappers.GithubReposToDomainFromEntityMapper
import com.mycomp.githubrepositories.data.mappers.GithubReposToDomainFromResponseMapper
import com.mycomp.githubrepositories.data.mappers.GithubReposToEntityFromDomainMapper
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository
import org.koin.android.ext.koin.androidApplication
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
            }
            .build()
    }

    single<GithubService> { get<Retrofit>().create(GithubService::class.java) }
}

val repositoryModule = module {
    single { GithubReposToDomainFromResponseMapper() }
    single { GithubReposToEntityFromDomainMapper() }
    single { GithubReposToDomainFromEntityMapper() }
    single<GithubReposRepository> { GithubReposRepositoryImpl(get(), get(), get(), get(), get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): GithubRepositoriesDatabase {
        return Room.databaseBuilder(
            application,
            GithubRepositoriesDatabase::class.java,
            "GithubRepositoriesDatabase"
        )
            .allowMainThreadQueries()
            .build()
    }

    single { provideDatabase(androidApplication()) }
    single { get<GithubRepositoriesDatabase>().githubRepositoryDao() }
}