package edu.towson.cosc435.alexander.planner.ui.nav

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.data.database.Task
import edu.towson.cosc435.alexander.planner.data.model.OldTask
import edu.towson.cosc435.alexander.planner.ui.TaskListView
import edu.towson.cosc435.alexander.planner.ui.calendar.Calendar
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.calendar.SelectedDatePage
import edu.towson.cosc435.alexander.planner.ui.newtask.NewTaskView
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListViewModel
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController()
) {
    val tasks = remember { mutableListOf<OldTask>() }
    tasks.add(OldTask("1", "Task 1", "This is going to test how much text can fit onto the card.", "22-4-2024", "Time", true))
    tasks.add(OldTask("2", "Task 2", "Can you imagine waking up on a Sunday? Well you gotta, no ifs or buts.", "30-4-2024", "Time", true))
    tasks.add(OldTask("3", "Task 3", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("4", "Task 4", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("5", "Task 5", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("6", "Task 6", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("7", "Task 7", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("8", "Task 8", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("9", "Task 9", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("10", "Task 10", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("11", "Task 11", "Summary 2", "30-4-2024", "Time", true))
    tasks.add(OldTask("12", "Task 12", "Summary 2", "30-4-2024", "Time", true))
    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
            composable(Routes.TaskList.route) {
                TaskListView(tasks = tasks)
            }
            composable(Routes.Calendar.route) {
                val vm: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                Calendar(viewModel = vm) { selectedDate ->
                    vm.setSelectedDate(selectedDate)
                    navController.navigate(Routes.SelectedDatePage.route)
                }
            }
            composable(Routes.TaskWizard.route) {
                val vm: TaskListViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                NewTaskView(onAddTask = { newTask: Task ->
                    vm.addTask(newTask)
                    navController.popBackStack() // navigate backwards!
                })

            }
            composable(Routes.SelectedDatePage.route) {
                val vm: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                val selectedDate = vm.selectedDate.observeAsState()
                selectedDate.value?.let { date ->
                    SelectedDatePage(date = date, tasks = vm.tasks)
                }
            }
        }
    }
}

