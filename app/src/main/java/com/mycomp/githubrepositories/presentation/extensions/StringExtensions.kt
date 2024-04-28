package com.mycomp.githubrepositories.presentation.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun String.openUrlInBrowser(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(this))
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No application found to open the link", Toast.LENGTH_LONG).show()
    }
}