package com.example.test.ui.views.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.data.models.User
import com.example.test.ui.theme.Colors
import com.example.test.ui.theme.Paddings
import com.example.test.ui.theme.Shapes

@Composable
fun UserItem(modifier: Modifier, user: User) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(Paddings.small),
        elevation = 6.dp,
        shape = Shapes.large,
        backgroundColor = Colors.primary
    ) {
        Row(
            modifier = Modifier
                .padding(Paddings.regular)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = user.firstName)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(
        modifier = Modifier.height(40.dp),
        user = User.EMPTY_USER.copy(firstName = "Name")
    )
}