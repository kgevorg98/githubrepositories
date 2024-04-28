package com.mycomp.githubrepositories.data.dataservice.sqlservice

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mycomp.githubrepositories.data.models.db.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class GithubRepositoriesDatabase : RoomDatabase() {
    abstract fun githubRepositoryDao(): GithubRepositoryDao
}