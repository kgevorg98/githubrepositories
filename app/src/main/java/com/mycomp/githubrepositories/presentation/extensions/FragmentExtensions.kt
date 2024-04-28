package com.mycomp.githubrepositories.presentation.extensions

import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <reified T> Fragment.onEach(flow: Flow<T>, crossinline action: (T) -> Unit) =
    view?.run {
        if (!this@onEach.isAdded) return@run
        flow.onEach { action(it ?: return@onEach) }.observeInLifecycle(viewLifecycleOwner)
    }