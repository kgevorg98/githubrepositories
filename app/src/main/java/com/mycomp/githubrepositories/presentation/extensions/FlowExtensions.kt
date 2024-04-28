package com.mycomp.githubrepositories.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import com.mycomp.githubrepositories.presentation.appbase.FlowObserver
import kotlinx.coroutines.flow.Flow

inline fun <reified T> Flow<T>.observeInLifecycle(
    lifecycleOwner: LifecycleOwner
) = FlowObserver(lifecycleOwner, this)