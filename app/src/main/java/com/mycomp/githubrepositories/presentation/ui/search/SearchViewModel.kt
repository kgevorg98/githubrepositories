package com.mycomp.githubrepositories.presentation.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mycomp.githubrepositories.core.Result
import com.mycomp.githubrepositories.domain.interactors.AddDownloadedRepoInteractor
import com.mycomp.githubrepositories.domain.interactors.GetGithubReposByUserNameInteractor
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.domain.repository.GithubReposRepository
import com.mycomp.githubrepositories.presentation.extensions.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val getGithubReposByUserNameInteractor: GetGithubReposByUserNameInteractor,
    private val addDownloadedRepoInteractor: AddDownloadedRepoInteractor,
) : ViewModel() {

    fun getGithubReposByUserName(userName: String) =
        getGithubReposByUserNameInteractor(userName).cachedIn(viewModelScope)


    fun addToDownloads(repoItem: RepositoryModel) {
        launch {
            addDownloadedRepoInteractor(repositoryModel = repoItem)
        }
    }
}