package com.olivierloukombo.android_app_github_api.navigation

sealed class Screen(val route: String){
    object SplashScreen : Screen("SplashScreen")
    object Main : Screen("Main")
    object Detail : Screen("Detail")
}
