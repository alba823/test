package com.example.test.ui.views.user

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.theme.Colors

@Composable
fun UserDivider() {
    Divider(
        modifier = Modifier.fillMaxWidth(0.8f),
        color = Colors.primary
    )
}

@Preview(showBackground = true)
@Composable
fun UserDividerPreview() {
    UserDivider()
}