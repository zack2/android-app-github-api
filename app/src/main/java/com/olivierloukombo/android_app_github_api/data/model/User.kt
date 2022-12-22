package com.olivierloukombo.android_app_github_api.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val id: Int, val avatar_url: String, val login: String)
