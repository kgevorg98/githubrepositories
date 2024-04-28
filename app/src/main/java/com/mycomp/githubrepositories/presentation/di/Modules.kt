package com.mycomp.githubrepositories.presentation.di

import com.mycomp.githubrepositories.presentation.ui.downloads.DownloadsViewModel
import com.mycomp.githubrepositories.presentation.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SearchViewModel(get(), get()) }
    viewModel { DownloadsViewModel(get()) }
}