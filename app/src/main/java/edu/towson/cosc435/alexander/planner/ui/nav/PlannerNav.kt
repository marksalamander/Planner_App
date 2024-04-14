package edu.towson.cosc435.alexander.planner.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.Task
import edu.towson.cosc435.alexander.planner.ui.TaskListView
import edu.towson.cosc435.alexander.planner.ui.TaskWizard
import edu.towson.cosc435.alexander.planner.ui.calendar.Calendar
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.calendar.SelectedDatePage
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme
import java.time.YearMonth


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController(),
    viewModel: CalendarViewModel
) {
    val tasks = remember { mutableStateListOf<Task>() }
    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
            composable(Routes.TaskList.route) {
                TaskListView()
            }
            composable(Routes.Calendar.route) {
                Calendar(current = YearMonth.now(), tasks = tasks) { selectedDate ->
                    viewModel.setSelectedDate(selectedDate)
                    navController.navigate(Routes.SelectedDatePage.route)
                }
            }
            composable(Routes.TaskWizard.route) {
                TaskWizard { task ->
                    tasks.add(task)
                }
            }
            composable(Routes.SelectedDatePage.route) {
                val selectedDate = viewModel.selectedDate.observeAsState()
                selectedDate.value?.let { date ->
                    SelectedDatePage(date = date)
                }
            }
        }
    }
}

