package com.olivierloukombo.android_app_github_api.di

import android.app.Application
import com.olivierloukombo.android_app_github_api.data.GithubUserRepoDatabase
import com.olivierloukombo.android_app_github_api.data.repository.DetailUserRepository
import com.olivierloukombo.android_app_github_api.data.repository.ListUserRepository
import com.olivierloukombo.android_app_github_api.network.ApiGithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideGithubUserRpoDatabase(/*@ApplicationContext*/ app: Application): GithubUserRepoDatabase {
        return GithubUserRepoDatabase.getInstance(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideListUserRepository(apiGithubService: ApiGithubService, db: GithubUserRepoDatabase): ListUserRepository {
        return ListUserRepository(apiGithubService, db.userDao())
    }

    @Provides
    @Singleton
    fun provideDetailUserRepository(apiGithubService: ApiGithubService, db: GithubUserRepoDatabase): DetailUserRepository {
        return DetailUserRepository(apiGithubService, db.repoDao())
    }

}