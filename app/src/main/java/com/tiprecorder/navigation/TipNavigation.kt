package com.tiprecorder.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddTip : Screen("add_tip")
}
