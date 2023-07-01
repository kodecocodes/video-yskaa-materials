package com.kodeco.android.navigation

sealed class Screens(val route: String) {
    object TaskListScreen : Screens("taskList")
    object TaskDetailsScreen: Screens("taskDetail")
}