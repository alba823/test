package com.example.test.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TestTheme(
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = Black
    )

    MaterialTheme(
        colors = Colors,
        shapes = Shapes,
        typography = Typography,
        content = content
    )
}