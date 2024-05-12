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
    navController: NavHostController = rememberNavController(),
    vm: TaskListViewModel
) {
//    val vm: TaskListViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)

//    tasks.add(Task(1, "Task 1", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(2, "Task 2", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(3, "Task 3", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(4, "Task 4", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(5, "Task 5", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(6, "Task 6", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(7, "Task 7", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(8, "Task 8", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(9, "Task 9", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(10, "Task 10", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(11, "Task 11", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))
//    tasks.add(Task(12, "Task 12", "This is going to test how much text can fit onto the card.", LocalDate.now(), LocalTime.now(), false, false))

    PlannerTheme {
        NavHost(navController, startDestination = Routes.Calendar.route) {
            composable(Routes.TaskListView.route) {
                TaskListView(
                    tasks = vm.tasks,
                    onDelete = vm::deleteTask,
                    onToggle = vm::toggleSelected,
                    onSelectItem = vm::selectTask
                )
            }
            composable(Routes.Calendar.route) {
                val vmc: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                Calendar(viewModel = vmc) { _ ->
                    navController.navigate(Routes.SelectedDatePage.route)
                }
            }
            composable(Routes.TaskWizard.route) {
                NewTaskView(onAddTask = { newTask: Task ->
                    vm.addTask(newTask)
                    navController.popBackStack() // navigate backwards!
                })
            }
            composable(Routes.SelectedDatePage.route) {
                val vmc: CalendarViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
                vmc.loadSelectedDateTasks()
                val selectedDate = vmc.selectedDate.observeAsState()
                selectedDate.value?.let { date ->
                    SelectedDatePage(date = date, tasks = vmc.dateTasks, onToggle = vmc::toggleSelected)
                }
            }
        }
    }
}

