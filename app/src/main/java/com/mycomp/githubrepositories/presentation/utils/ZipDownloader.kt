package com.mycomp.githubrepositories.presentation.utils

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.mycomp.githubrepositories.BuildConfig

class ZipDownloader(
    private val context: Context
) : Downloader {
    companion object {
        const val PREFIX_URL = "repos/"
        const val POSTFIX_URL = "/zipball"
    }

    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun download(urlPart: String): Long {
        val downloadUrl = "${BuildConfig.BASE_URL}$PREFIX_URL$urlPart$POSTFIX_URL"
        Log.e("@err", downloadUrl)
        val request = DownloadManager.Request(downloadUrl.toUri())
            .setMimeType("application/x-zip")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("${urlPart.substringAfterLast("/")}.zip")
            .addRequestHeader("Authorization", BuildConfig.AUTH_TOKEN)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                urlPart.substringAfterLast("/")
            )
        return downloadManager.enqueue(request)
    }

}