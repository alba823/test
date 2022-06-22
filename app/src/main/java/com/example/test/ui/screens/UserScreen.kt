package com.example.test.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test.R
import com.example.test.ui.views.user.UserDivider
import com.example.test.ui.views.user.UserValue
import com.example.test.viewModels.UserViewModel

@Composable
fun UserScreen() {
    val viewModel: UserViewModel = hiltViewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserValue(
            name = stringResource(R.string.name_label),
            value = viewModel.user.value.firstName.plus(stringResource(R.string.space_divider)).plus(viewModel.user.value.lastName)
        )
        UserDivider()
        UserValue(
            name = stringResource(R.string.age_label),
            value = viewModel.user.value.age
        )
        UserDivider()
        UserValue(
            name = stringResource(R.string.genre_label),
            value = viewModel.user.value.gender
        )
        UserDivider()
        UserValue(
            name = stringResource(R.string.country_label),
            value = viewModel.user.value.country
        )
    }
}



