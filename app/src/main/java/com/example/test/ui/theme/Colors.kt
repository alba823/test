package com.example.test.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val LightViolet = Color(0xFFB37EE6)
val DarkGrey = Color(0xFF232323)
val Black = Color.Black
val LightGrey = Color(0xFF474747)

@SuppressLint("ConflictingOnColor")
val Colors = lightColors(
    primary = LightGrey,
    onPrimary = LightViolet,
    secondary = DarkGrey,
    background = DarkGrey,
    onSecondary = LightViolet,
    onBackground = Color.White
)