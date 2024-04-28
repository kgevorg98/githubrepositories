package com.mycomp.githubrepositories.presentation.ui.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mycomp.githubrepositories.domain.models.RepositoryModel

class ReposDiffCallback : DiffUtil.ItemCallback<RepositoryModel>() {
    override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
        oldItem == newItem

}
