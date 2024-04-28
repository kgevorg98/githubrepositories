package com.mycomp.githubrepositories.core

sealed class Result<S>(val data: S? = null, val message: String? = null) {
    class Success<S>(data: S?) : Result<S>(data)
    class Error<S>(message: String, data: S? = null) : Result<S>(data, message)
    class Loading<S>(data: S? = null) : Result<S>(data)
}
