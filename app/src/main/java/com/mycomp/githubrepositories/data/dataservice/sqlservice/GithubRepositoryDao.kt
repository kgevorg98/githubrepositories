package com.mycomp.githubrepositories.data.dataservice.sqlservice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycomp.githubrepositories.data.models.db.RepositoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GithubRepositoryDao {
    @Query("SELECT * FROM repos")
    suspend fun getDownloadedRepos(): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubDownloadedRepo(item: RepositoryEntity)

    @Delete
    suspend fun deleteRepo(item: RepositoryEntity): Int

    @Query("DELETE FROM repos WHERE fullName = :fullName")
    suspend fun deleteRepoFromDownloadsByFullName(fullName: String)
}