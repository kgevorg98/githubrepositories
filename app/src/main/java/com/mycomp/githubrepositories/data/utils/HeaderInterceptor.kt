package com.mycomp.githubrepositories.data.utils

import com.mycomp.githubrepositories.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
           // .addHeader("Authorization", "Bearer ${BuildConfig.AUTH_TOKEN}")
            .build()
        return chain.proceed(newRequest)
    }
}