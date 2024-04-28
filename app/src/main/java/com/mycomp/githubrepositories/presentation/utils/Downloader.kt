package com.mycomp.githubrepositories.presentation.utils

interface Downloader {
    fun download(url:String):Long
}