package com.example.test.ui.navigation

abstract class NavigationItem {
    abstract val route: String
}

sealed class NavigationItems : NavigationItem() {
    object MainScreen : NavigationItems() {
        override val route: String
            get() = "main_screen"
    }

    object UserScreen : NavigationItems() {
        const val USER_ARG = "user"

        override val route: String
            get() = "user_screen"
    }
}