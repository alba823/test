package com.example.test.ui.views.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.R
import com.example.test.ui.theme.Paddings
import com.example.test.ui.theme.Typography

@Composable
fun UsersLabel(totalUsers: Int, expectedUsers: Int) {
    Text(
        modifier = Modifier.padding(Paddings.regular),
        text = stringResource(R.string.users_label).plus(" $totalUsers/$expectedUsers"),
        style = Typography.h1
    )
}

@Preview(showBackground = true)
@Composable
fun UsersLabelPreview() {
    UsersLabel(2, 2)
}