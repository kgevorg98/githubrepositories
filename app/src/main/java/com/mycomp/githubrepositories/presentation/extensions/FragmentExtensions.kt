package com.mycomp.githubrepositories.presentation.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <reified T> Fragment.onEach(flow: Flow<T>, crossinline action: (T) -> Unit) =
    view?.run {
        if (!this@onEach.isAdded) return@run
        flow.onEach { action(it ?: return@onEach) }.observeInLifecycle(viewLifecycleOwner)
    }

fun Fragment.isNetWorkAvailable(): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val capabilities =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}