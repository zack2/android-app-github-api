package com.olivierloukombo.android_app_github_api.di

import androidx.lifecycle.ViewModel
import com.olivierloukombo.android_app_github_api.network.ApiGithubService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiGithubService(): ApiGithubService {
        return ApiGithubService.getInstance()
    }

}