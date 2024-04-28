package com.mycomp.githubrepositories.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mycomp.githubrepositories.R
import com.mycomp.githubrepositories.databinding.ItemRepositoryBinding
import com.mycomp.githubrepositories.domain.models.RepositoryModel
import com.mycomp.githubrepositories.presentation.extensions.openUrlInBrowser


class ReposAdapter(private val onDownloadClickListener: (item: RepositoryModel) -> Unit) :
    PagingDataAdapter<RepositoryModel, ReposAdapter.ViewHolder>(ReposDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, onDownloadClickListener)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositoryModel, onDownloadClickListener: (item: RepositoryModel) -> Unit) {
            with(binding) {
                repoNumber.text = (bindingAdapterPosition+1).toString()
                repoTitle.text = item.name
                ivDownload.setOnClickListener {
                    onDownloadClickListener(item)
                }
                repoTitle.setOnClickListener {
                    item.htmlUrl.openUrlInBrowser(root.context)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}