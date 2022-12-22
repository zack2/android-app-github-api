package com.olivierloukombo.android_app_github_api.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.olivierloukombo.android_app_github_api.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(updateUser: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE  FROM user")
    suspend fun deleteUsers()
}
