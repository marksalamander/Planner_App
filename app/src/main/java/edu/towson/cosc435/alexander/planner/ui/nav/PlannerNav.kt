package edu.towson.cosc435.alexander.planner.ui.nav

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.calendar.Calendar
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.calendar.SelectedDatePage
import edu.towson.cosc435.alexander.planner.ui.newtask.NewTaskView
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListView
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListViewModel
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlannerNav(
    navController: NavHostController = rememberNavController()
) {
    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
            composable(Routes.TaskListView.route) {
                val vm: TaskListViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                TaskListView(
                    vm = vm,
                    selectedTask = vm.selectedTask,
                    onDelete = vm::deleteTask,
                    onToggle = vm::toggleSelected,
                    onSelectItem = vm::selectTask
                )
            }
            composable(Routes.Calendar.route) {
                val vmc: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                Calendar(viewModel = vmc) { _ -> // Navigates to SelectedDatePage when a date is clicked ~Mark
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
                val vmc: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                SelectedDatePage(
                    date = vmc.selectedDate,
                    tasks = vmc.dateTasks,
                    onDelete = vmc::deleteTask,
                    onToggle = vmc::toggleSelected,
                    onSelectItem = vmc::selectTask
                )
            }
        }
    }
}

