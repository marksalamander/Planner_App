package edu.towson.cosc435.alexander.planner.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.nav.PlannerNav
import edu.towson.cosc435.alexander.planner.ui.nav.Routes
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    val vm: TaskListViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    //val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopBar(viewModel = viewModel())
        },
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        PlannerNav(navController = nav, vm)
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
            selected = currentRoute == Routes.TaskListView.route,
            onClick = {
                nav.navigate(Routes.TaskListView.route) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable

private fun TopBar(viewModel: TaskListViewModel) {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        title = { Text("Task Planner")},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        actions = {
            IconButton(onClick = {
                activity?.finish()
            }) {
                Icon(Icons.Default.Close, contentDescription = "Delete")
            }
        }
    )
}