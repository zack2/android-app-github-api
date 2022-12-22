package com.olivierloukombo.android_app_github_api.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.olivierloukombo.android_app_github_api.components.CircularProgress
import com.olivierloukombo.android_app_github_api.components.ToolbarDetail
import com.olivierloukombo.android_app_github_api.components.ToolbarDetailPrev
import com.olivierloukombo.android_app_github_api.data.model.Repo
import com.olivierloukombo.android_app_github_api.data.model.User
import com.olivierloukombo.android_app_github_api.data.repository.DetailUserRepository
import com.olivierloukombo.android_app_github_api.navigation.Screen
import com.olivierloukombo.android_app_github_api.ui.theme.AndroidappgithubapiTheme
import com.olivierloukombo.android_app_github_api.vm.DetailUserViewModel
import kotlinx.coroutines.flow.map

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "FlowOperatorInvokedInComposition",
    "StateFlowValueCalledInComposition"
)
@Composable
fun DetailUser(navController: NavHostController, viewModel: DetailUserViewModel = hiltViewModel()) {

    AndroidappgithubapiTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ToolbarDetail(navController = navController)
            }
        ) {
            //get the argument
            navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                "login"
            ).apply {
                 viewModel.loadListUserRepo(this!!)
            }
            val state = viewModel.state.collectAsState()
            //show circular progress before fetch data
            if (state.value.isEmpty())
                CircularProgress()
            RepoList( repos = state.value)
        }
    }
}

@Composable
private fun RepoList(repos: List<Repo>) {
    LazyColumn(state = rememberLazyListState()) {
        items(
            items = repos,
            key = { repo ->
                repo.id
            }
        ) { repo ->
            CardRepo(repo = repo)
        }

    }
}

@Composable
fun CardRepo(repo: Repo) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.html_url))
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onSecondary)
            .clickable {
                startActivity(context, intent, null)
            },
        shape = RoundedCornerShape(0.dp), elevation = 4.dp
    ) {

            Row{

                Column(
                    verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        //.weight(0.8f)
                ) {
                    Text(
                        text = "name :",
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )

                    Text(
                        text = "updated at :",
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )
                    Text(
                        text = "stargazers count :",
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )
                    Text(
                        text = "language :",
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )
                }

                Column(
                    verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                       // .weight(0.8f)
                ) {
                    Text(
                        text = repo.name,
                        style = MaterialTheme.typography.h1,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )

                    Text(
                        text = repo.updated_at,
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )
                    Text(
                        text = repo.stargazers_count,
                        style = MaterialTheme.typography.h2,
                        maxLines = 1,
                        overflow = TextOverflow.Clip

                    )
                    repo.language?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h2,
                            maxLines = 1,
                            overflow = TextOverflow.Clip

                        )
                    }
                }
            }
        }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DetailPreview() {

    AndroidappgithubapiTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ToolbarDetailPrev()
            }
        ) {

        }
    }


}
