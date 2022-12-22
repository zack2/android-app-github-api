package com.olivierloukombo.android_app_github_api.vm

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivierloukombo.android_app_github_api.data.model.Repo
import com.olivierloukombo.android_app_github_api.data.model.User
import com.olivierloukombo.android_app_github_api.data.repository.DetailUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val detailUserRepository: DetailUserRepository
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<Repo>())
    val state: StateFlow<List<Repo>>
        get() = _state

    fun loadListUserRepo(argument: String){
        viewModelScope.launch {
            detailUserRepository.loadListUserRepo(argument)
        }
        getUserRepos()
    }
     private fun getUserRepos(): List<Repo> {
        var repos: List<Repo>  = emptyList()
        viewModelScope.launch{
            detailUserRepository.getUserRepoFromCache().observeForever {
                repos = it
                _state.value = repos
            }

        }
        return repos
    }

    companion object{
        private val TAG  = DetailUserViewModel::class.java.simpleName
    }
}