package com.olivierloukombo.android_app_github_api.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.olivierloukombo.android_app_github_api.data.UserDao
import com.olivierloukombo.android_app_github_api.data.model.User
import com.olivierloukombo.android_app_github_api.network.ApiGithubService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListUserRepository @Inject constructor(private val apiGithubService: ApiGithubService, private val userDao: UserDao) {

    suspend fun loadListUser(){
        apiGithubService.getUsers().map {
            userDao.insertUser(it)
        }
    }

     fun getUserFromCache() :LiveData<List<User>>{
        return userDao.getUsers()
    }
}