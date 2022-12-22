package com.olivierloukombo.android_app_github_api.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivierloukombo.android_app_github_api.data.model.User
import com.olivierloukombo.android_app_github_api.data.repository.ListUserRepository
import dagger.Binds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val repository: ListUserRepository
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<User>())
    val state: StateFlow<List<User>>
        get() = _state

    init {
        viewModelScope.launch {
            repository.loadListUser()
        }
        getUsers()
    }

    private fun getUsers(): List<User> {
        var users: List<User> = emptyList()
        viewModelScope.launch {
              repository.getUserFromCache().observeForever {
                  users = it
                  _state.value = users
              }
        }
        return users
    }


}