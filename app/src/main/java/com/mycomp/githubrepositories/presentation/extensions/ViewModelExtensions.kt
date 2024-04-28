package com.mycomp.githubrepositories.presentation.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.launch(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    func: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(coroutineContext) { func() }
}