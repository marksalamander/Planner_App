package edu.towson.cosc435.alexander.planner.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.nav.PlannerNav
import edu.towson.cosc435.alexander.planner.ui.nav.Routes
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: CalendarViewModel
) {
    val nav = rememberNavController()
    val vm: TaskListViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        PlannerNav(navController = nav, viewModel = viewModel)
    }
}

@Composable
private fun BottomBar(
    nav: NavHostController
) {
    val currentBackStack by nav.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    NavigationBar() {
        NavigationBarItem(
            selected = currentRoute == Routes.Calendar.route,
            onClick = {
                nav.navigate(Routes.Calendar.route) {
                    launchSingleTop = true
                    popUpTo(Routes.Calendar.route)
                }
            },
            icon = {
                Icon(Icons.Default.DateRange, "")
            },
            label = {
                Text("Calendar")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.TaskList.route,
            onClick = {
                nav.navigate(Routes.TaskList.route) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(Icons.Default.List, "")
            },

            label = {
                Text("Task List")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.TaskWizard.route,
            onClick = {
                nav.navigate(Routes.TaskWizard.route) {
                    launchSingleTop = true
                    popUpTo(Routes.TaskWizard.route)
                }
            },

            icon = {
                Icon(Icons.Default.AddCircle, "")
            },

            label = {
                Text("Add Task")
            }
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun TopBar(viewModel: TaskListViewModel) {
//    TopAppBar(
//        title = { Text("Assignment4")},
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            titleContentColor = MaterialTheme.colorScheme.onPrimary,
//            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
//            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
//        ),
//        actions = {
//            if (viewModel.anyTasksSelected) {
//                IconButton(onClick = {
//                    viewModel.toggleDeleteModal()
//                }) {
//                    Icon(Icons.Default.Delete, contentDescription = "Delete")
//                }
//            }
//        }
//    )
//}