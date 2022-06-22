package com.example.test.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test.data.models.User
import com.example.test.data.models.response.ErrorCode
import com.example.test.data.models.response.ResponseState
import com.example.test.ui.views.statehandle.ErrorView
import com.example.test.ui.views.statehandle.LoadingView
import com.example.test.ui.views.user.UsersList
import com.example.test.viewModels.MainViewModel

@Composable
fun MainScreen(onUserClick: (User) -> Unit) {
    val viewModel: MainViewModel = hiltViewModel()

    when (val state = viewModel.userIds.value) {
        is ResponseState.Loading -> {
            LoadingView()
        }
        is ResponseState.Error -> {
            ErrorView(
                modifier = Modifier.fillMaxSize(),
                errorCode = state.code,
                onRefreshClick = { viewModel.loadUsers() }
            )
        }
        is ResponseState.Success -> {
            with(viewModel) {
                if (state.result.data?.size == usersCount.value && users.value.isEmpty()) { //Additional check, in case if all of get user requests failed
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        errorCode = ErrorCode.BAD_RESPONSE_CODE,
                        onRefreshClick = { viewModel.loadUsers() }
                    )
                } else {
                    UsersList(
                        users = users.value,
                        expectedCount = usersCount.value,
                        onUserClick = onUserClick
                    )
                }
            }
        }
    }
}