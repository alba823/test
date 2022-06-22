package com.example.test.ui.views.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.data.models.User

@Composable
fun UsersList(users: List<User>, expectedCount: Int, onUserClick: (User) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            UsersLabel(users.size, expectedCount)
        }
        items(users) { user ->
            UserItem(
                modifier = Modifier.clickable {
                    onUserClick(user)
                },
                user = user
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersListPreview() {
    UsersList(
        users = listOf(
            User.EMPTY_USER.copy(firstName = "Name One"),
            User.EMPTY_USER.copy(firstName = "Name Two")
        ),
        2
    ) {}
}