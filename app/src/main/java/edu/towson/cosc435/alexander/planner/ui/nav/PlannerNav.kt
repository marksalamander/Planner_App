package edu.towson.cosc435.alexander.planner.ui.nav

import TaskWizard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.TaskListView
import edu.towson.cosc435.alexander.planner.ui.calendar.Calendar
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.calendar.SelectedDatePage
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController(),
    viewModel: CalendarViewModel
) {
    val tasks = remember { mutableListOf<Task>() }
    tasks.add(Task("1", "Task 1", "This is going to test how much text can fit onto the card.", "22-4-2024", "Time", true))
    tasks.add(Task("2", "Task 2", "Can you imagine waking up on a Sunday? Well you gotta, no ifs or buts.", "30-4-2024", "Time", true))
    tasks.add(Task("3", "Task 3", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("4", "Task 4", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("5", "Task 5", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("6", "Task 6", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("7", "Task 7", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("8", "Task 8", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("9", "Task 9", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("10", "Task 10", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("11", "Task 11", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(Task("12", "Task 12", "Summary 2", "30-4-2024", "Time", true))
    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
            composable(Routes.TaskList.route) {
                TaskListView(tasks = tasks)
            }
            composable(Routes.Calendar.route) {
                Calendar(tasks = tasks) { selectedDate, currentTasks ->
                    viewModel.setSelectedDate(selectedDate, currentTasks)
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
                    SelectedDatePage(date = date, tasks = viewModel.tasks)
                }
            }
        }
    }
}

