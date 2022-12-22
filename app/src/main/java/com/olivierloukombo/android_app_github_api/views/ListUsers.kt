package com.olivierloukombo.android_app_github_api.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.olivierloukombo.android_app_github_api.components.CircularProgress
import com.olivierloukombo.android_app_github_api.components.Toolbar
import com.olivierloukombo.android_app_github_api.data.model.User
import com.olivierloukombo.android_app_github_api.navigation.Screen
import com.olivierloukombo.android_app_github_api.ui.theme.AndroidappgithubapiTheme
import com.olivierloukombo.android_app_github_api.vm.ListUserViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlin.coroutines.coroutineContext


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FlowOperatorInvokedInComposition")
@Composable
fun ListUsers(navController: NavHostController, viewModel: ListUserViewModel) {

    AndroidappgithubapiTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar()
            }
        ) {

            val state = viewModel.state.collectAsState()

            //show circular progress before fetch data
            if (state.value.isEmpty())
                CircularProgress()
            UserList(navController, users = state.value)


        }


    }
}


@Composable
private fun UserList(navController: NavHostController, users: List<User>) {
    LazyColumn(state = rememberLazyListState()) {
        items(
            items = users,
            key = { user ->
                user.id
            }
        ) { user ->
            UserItem(navController, user = user)
        }

    }
}

@Composable
fun UserItem(navController: NavHostController, user: User) {


    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onSecondary)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("login", user.login)
                }
                navController.navigate(
                    Screen.Detail.route
                )
            },
        shape = RoundedCornerShape(0.dp), elevation = 4.dp
    ) {
        Surface() {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
               // horizontalArrangement = Arrangement.Center
            ) {
                ProfilePic(user.avatar_url)
                profileDetail(user = user)
            }
        }
    }


}

@Composable
fun UserItemPreview(user: User) {
   // Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
               // .wrapContentSize()
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = MaterialTheme.colors.onSecondary),
            shape = RoundedCornerShape(0.dp), elevation = 4.dp
        ) {
            Row {
                ProfilePic(avatarUrl = user.avatar_url)
                profileDetail(user = user)
            }

        }

   // }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePic(avatarUrl: String) {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.onSecondary),
        modifier = Modifier
            .size(84.dp)
           // .padding(8.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
        elevation = 4.dp
    ) {
        GlideImage(
            model = avatarUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(84.dp)
               // .padding(8.dp)
               // .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    ,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun profileDetail(user: User) {
    Column(
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxHeight()

    ) {
        Text(
            text = user.login,
            style = MaterialTheme.typography.h1,
            maxLines = 1,
            overflow = TextOverflow.Clip

        )
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    AndroidappgithubapiTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Toolbar()
            }
        ) {

            User(
                1,
                "https://avatars.githubusercontent.com/u/1?v=4",
                "mojombo",

                ).let {
                UserItemPreview(it)
            }
        }


    }
}