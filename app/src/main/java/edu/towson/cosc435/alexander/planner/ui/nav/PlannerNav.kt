package edu.towson.cosc435.alexander.planner.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.Calendar
import edu.towson.cosc435.alexander.planner.ui.TaskListView
import edu.towson.cosc435.alexander.planner.ui.TaskWizard
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme

@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController()
) {
    PlannerTheme {
        NavHost(navController, startDestination = Routes.TaskList.route) {
            composable(Routes.TaskList.route) {
                TaskListView()
            }
            composable(Routes.Calendar.route) {
                Calendar()
            }
            composable(Routes.TaskWizard.route) {
                TaskWizard()
            }
        }
    }
}

