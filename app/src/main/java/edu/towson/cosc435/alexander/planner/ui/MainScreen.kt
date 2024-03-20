package edu.towson.cosc435.alexander.planner.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.alexander.planner.ui.nav.PlannerNav
import edu.towson.cosc435.alexander.planner.ui.nav.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val nav = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        PlannerNav(nav)
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