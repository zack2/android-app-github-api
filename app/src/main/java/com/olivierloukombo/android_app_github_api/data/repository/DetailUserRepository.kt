package com.olivierloukombo.android_app_github_api.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.olivierloukombo.android_app_github_api.data.RepoDao
import com.olivierloukombo.android_app_github_api.data.model.Repo
import com.olivierloukombo.android_app_github_api.network.ApiGithubService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUserRepository @Inject constructor(private val apiGithubService: ApiGithubService, private val repoDao: RepoDao)  {
    suspend fun loadListUserRepo(login:String){
        apiGithubService.getRepos(login).map {
            Log.i("#########TAG", "DetailRepository: ${it.name} et ${it.html_url}", )
            repoDao.insertRepo(it)
        }
    }

    fun getUserRepoFromCache() : LiveData<List<Repo>> {
        return repoDao.getRepos()
    }

    companion object{
        private val TAG  = DetailUserRepository::class.java.simpleName
    }
}