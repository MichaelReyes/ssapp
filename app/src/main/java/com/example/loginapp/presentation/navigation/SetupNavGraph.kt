package com.example.loginapp.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginapp.presentation.navigation.tool.AUTH_GRAPH_ROUTE
import com.example.loginapp.presentation.navigation.tool.Screen
import com.example.loginapp.presentation.screen.authentication.LoginScreen
import com.example.loginapp.presentation.screen.home.WelcomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_ROUTE,
    ) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Welcome.route) { WelcomeScreen() }
    }
}