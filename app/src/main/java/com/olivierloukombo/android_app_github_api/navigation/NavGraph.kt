package com.olivierloukombo.android_app_github_api.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.olivierloukombo.android_app_github_api.navigation.Screen
import com.olivierloukombo.android_app_github_api.views.DetailUser
import com.olivierloukombo.android_app_github_api.views.ListUsers
import com.olivierloukombo.android_app_github_api.views.SplashScreen
import com.olivierloukombo.android_app_github_api.vm.DetailUserViewModel
import com.olivierloukombo.android_app_github_api.vm.ListUserViewModel
import javax.inject.Inject


@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            val listNoteViewModel: ListUserViewModel = hiltViewModel()
            ListUsers(navController, listNoteViewModel)
        }
        composable(route = Screen.Detail.route) {
            val detailUserViewModel: DetailUserViewModel = hiltViewModel()
            DetailUser(navController, detailUserViewModel)
        }
    }

}