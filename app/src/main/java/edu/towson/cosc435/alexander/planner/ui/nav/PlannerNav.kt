package edu.towson.cosc435.alexander.planner.ui.nav

import Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.TaskListView
import edu.towson.cosc435.alexander.planner.ui.TaskWizard
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController()
) {
    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
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

