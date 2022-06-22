package com.example.test.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.models.User
import com.example.test.data.models.response.ResponseModel
import com.example.test.data.models.response.ResponseState
import com.example.test.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _userIds: MutableState<ResponseState<ResponseModel<List<String>>>> =
        mutableStateOf(ResponseState.Loading())
    val userIds: State<ResponseState<ResponseModel<List<String>>>> = _userIds

    private var _usersCount: MutableState<Int> = mutableStateOf(0)
    val usersCount: State<Int> = _usersCount

    private var _users: MutableState<List<User>> = mutableStateOf(listOf())
    val users: State<List<User>> = _users

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _usersCount.value = 0
            repository.getUserIds().collect { response ->
                _userIds.value = response
                if (response is ResponseState.Success) {
                    response.result.data?.onEach { id ->
                        _usersCount.value++
                        loadUser(id)
                    }
                }
            }
        }
    }

    private suspend fun loadUser(id: String) {
        repository.getUserById(id).collect { response ->
            if (response is ResponseState.Success) {
                response.result.data?.let { user ->
                    _users.value = _users.value.plus(user)
                }
            }
        }
    }
}