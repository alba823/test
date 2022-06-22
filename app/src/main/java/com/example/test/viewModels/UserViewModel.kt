package com.example.test.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.test.data.models.User
import com.example.test.ui.navigation.NavigationItems
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _user = mutableStateOf(User.EMPTY_USER)
    val user: State<User> = _user

    init {
        val userArg: User? = savedStateHandle[NavigationItems.UserScreen.USER_ARG]
        userArg?.let { arg ->
            _user.value = arg
        }
    }
}