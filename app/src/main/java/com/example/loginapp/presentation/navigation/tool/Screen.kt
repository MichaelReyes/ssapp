package com.example.loginapp.presentation.navigation.tool

const val AUTH_GRAPH_ROUTE = "auth"
const val WELCOME_GRAPH_ROUTE = "welcome"

sealed class Screen(
    val route: String
) {
    object Login : Screen(AUTH_GRAPH_ROUTE)
    object Welcome : Screen(WELCOME_GRAPH_ROUTE)
}