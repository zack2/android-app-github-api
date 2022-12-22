package com.olivierloukombo.android_app_github_api.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.olivierloukombo.android_app_github_api.data.model.Repo
import com.olivierloukombo.android_app_github_api.data.model.User

@Database(
    entities = [User::class, Repo::class],
    version = 1
)
abstract class GithubUserRepoDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao

    companion object {
        private const val DATABASE_NAME = "notes_db"
        private var INSTANCE: GithubUserRepoDatabase? = null

        fun getInstance(context: Context): GithubUserRepoDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GithubUserRepoDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}