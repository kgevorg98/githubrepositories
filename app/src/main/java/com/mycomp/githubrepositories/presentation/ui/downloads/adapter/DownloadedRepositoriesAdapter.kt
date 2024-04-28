package com.mycomp.githubrepositories.presentation.ui.downloads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mycomp.githubrepositories.R
import com.mycomp.githubrepositories.databinding.ItemDownloadBinding
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.presentation.extensions.openUrlInBrowser
import com.mycomp.githubrepositories.presentation.ui.search.adapter.ReposDiffCallback

class DownloadedRepositoriesAdapter : ListAdapter<RepositoryModel, RecyclerView.ViewHolder>(
    ReposDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(
            ItemDownloadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoViewHolder).bind(getItem(position))
    }

    override fun getItemViewType(position: Int) =
        R.layout.item_download

    inner class RepoViewHolder(
        val binding: ItemDownloadBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositoryModel) {
            with(binding) {
                tvUserName.text = item.fullName.substringBeforeLast("/")
                tvRepoTitle.text = item.fullName.substringAfterLast("/")
                tvRepoTitle.setOnClickListener {
                    item.htmlUrl.openUrlInBrowser(root.context)
                }
            }
        }
    }
}
