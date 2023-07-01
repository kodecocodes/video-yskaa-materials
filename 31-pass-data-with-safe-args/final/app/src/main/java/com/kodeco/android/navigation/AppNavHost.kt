package com.kodeco.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.android.views.TaskDetailScreen
import com.kodeco.android.views.TaskListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.TaskListScreen.route
    ) {
        composable(Screens.TaskListScreen.route) {
            TaskListScreen(
                navigate = { taskListName ->
                    navController.navigate("${Screens.TaskDetailsScreen.route}/$taskListName")
                }
            )
        }
        composable(
            route = "${Screens.TaskDetailsScreen.route}/{taskListName}",
            arguments = listOf(navArgument("taskListName") {
                type = NavType.StringType
            })
        ) {
            TaskDetailScreen(
                taskName = it.arguments?.getString("taskListName"),
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}