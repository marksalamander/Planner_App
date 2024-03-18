package edu.towson.cosc435.alexander.planner

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme

// TODO: Import navigation (for some reason I can't find the correct statements)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerApp()
        }
    }
}

@Composable
fun PlannerApp() {
    val navController = rememberNavController()
    val selectedTab = remember { mutableStateOf(0) }

    PlannerTheme {
        NavHost(navController, startDestination = "taskList") {
            composable("taskList") {
                TaskListView(tasks = dummyTasks)
            }
            composable("calendar") {
                CalendarView()
            }
            composable("taskWizard") {
                TaskWizard()
            }
        }
    }
}

// Composable function for the calendar page
@Composable
fun CalendarView() {
    // TODO: Replace this placeholder text with actual calendar page implementation
    Text(text = "Calendar View")
}

// Composable function for the page to create/read/update/delete tasks/events
@Composable
fun TaskWizard() {
    // TODO: Replace this placeholder text with actual task wizard implementation
    Text(text = "Task Wizard")
}

// Composable function for the list of tasks displayed on the task list page
@Composable
fun TaskListView(tasks: List<Task>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // App title
        Text(
            text = "Task Planner",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Heading for task list
        Text(
            text = "Tasks",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Task list
        LazyColumn {
            // TODO: Implement listing TaskItems using this LazyColumn
        }
    }
}

// Composable functions for the individual task listings on the task list
@Composable
fun TaskItem(task: Task) {
    Text(
        text = task.title,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

// Data structure for Tasks
data class Task(val id: Int, val title: String, val description: String)

// Temporary list of placeholder Tasks for testing
val dummyTasks = listOf(
    Task(1, "Task 1", "Description of Task 1"),
    Task(2, "Task 2", "Description of Task 2"),
    Task(3, "Task 3", "Description of Task 3")
)