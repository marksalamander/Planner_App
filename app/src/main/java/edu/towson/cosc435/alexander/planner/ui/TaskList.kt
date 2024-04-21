package edu.towson.cosc435.alexander.planner.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.alexander.planner.data.model.Task

// Composable function for the list of tasks displayed on the task list page
@ExperimentalFoundationApi
@Composable
fun TaskListView(
    tasks: List<Task>
)
{
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
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
        ) {
            // TODO: Implement listing TaskItems using this LazyColumn
            items(items = tasks) { item ->
                // How each item in myArray is displayed in the LazyColumn
                Box(modifier = Modifier
                    .border(2.dp, Color.Black)
                    .padding(5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp) // Add space around each item for visibility
                            .padding(5.dp) // Add space around each item for visibility
                            .fillMaxWidth()
                    ) {
                        Text(text = item.title, style = MaterialTheme.typography.titleLarge)

                        Text(text = item.description, style = MaterialTheme.typography.bodyLarge)

                    }
                }
            }
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