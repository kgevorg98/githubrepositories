package com.mycomp.githubrepositories.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
)