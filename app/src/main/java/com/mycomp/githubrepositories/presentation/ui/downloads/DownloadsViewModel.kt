package com.mycomp.githubrepositories.presentation.ui.downloads

import androidx.lifecycle.ViewModel
import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.domain.interactors.GetDownloadedRepositoriesInteractor
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.presentation.extensions.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DownloadsViewModel(
    private val getDownloadedRepositoriesInteractor: GetDownloadedRepositoriesInteractor
) : ViewModel() {
    private val _getDownloadedRepos: MutableStateFlow<List<RepositoryModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val getDownloadedRepos = _getDownloadedRepos.asStateFlow()

    init {
        getDownloadedRepositories()
    }

    private fun getDownloadedRepositories() = launch {
        getDownloadedRepositoriesInteractor().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _getDownloadedRepos.value = result.data
                }

                else -> {
                }
            }
        }.launchIn(this)
    }
}