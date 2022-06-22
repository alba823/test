package com.example.test.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test.ui.screens.MainScreen
import com.example.test.ui.screens.UserScreen
import com.example.test.data.models.UserNavType
import com.example.test.utils.toJson
import com.example.test.ui.navigation.NavigationItems.MainScreen as Main
import com.example.test.ui.navigation.NavigationItems.UserScreen as User

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main.route) {
        composable(
            route = Main.route
        ) {
            MainScreen { user ->
                navController.navigate(
                    User.route.plus("/${user.toJson()}")
                )
            }
        }
        composable(
            route = User.route.plus("/{${User.USER_ARG}}"),
            arguments = listOf(
                navArgument(User.USER_ARG) { type = UserNavType() }
            )) {
            UserScreen()
        }
    }
}