package com.olivierloukombo.android_app_github_api.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repo(@PrimaryKey val id: Int, val name: String, val updated_at: String, val stargazers_count: String, val language: String?, val html_url: String)
