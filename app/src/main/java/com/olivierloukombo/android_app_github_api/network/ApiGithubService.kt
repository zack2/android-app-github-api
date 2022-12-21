package com.olivierloukombo.android_app_github_api.network

import com.olivierloukombo.android_app_github_api.data.model.Repo
import com.olivierloukombo.android_app_github_api.data.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGithubService {
    @GET("users")
    suspend fun getUsers(): List<User>
    @GET("/users/{login}/repos")
    suspend fun getRepos(@Path("login") login:String): List<Repo>


    companion object {
        private var api: ApiGithubService? = null
        private const val BASE_URL: String = "https://api.github.com/"


        fun getInstance(): ApiGithubService {
            if (api == null) {
                api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiGithubService::class.java)
            }
            return api!!
        }
    }
}