package com.olivierloukombo.android_app_github_api.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.olivierloukombo.android_app_github_api.data.model.Repo
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo")
     fun getRepos(): LiveData<List<Repo>>

    @Query("SELECT * FROM repo WHERE id = :id")
    suspend fun getRepoById(id: Int): Repo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: Repo)

    @Update
    suspend fun updateRepo(updateRepo: Repo)

    @Delete
    suspend fun deleteRepo(repo: Repo)

    @Query("DELETE  FROM repo")
    suspend fun deleteRepos()
}