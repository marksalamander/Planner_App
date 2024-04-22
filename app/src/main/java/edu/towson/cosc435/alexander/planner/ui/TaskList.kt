package edu.towson.cosc435.alexander.planner.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.Task

// Composable function for the list of tasks displayed on the task list page
@ExperimentalFoundationApi
@Composable
fun TaskListView(
    tasks: List<Task>
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // App title
            Text(
                text = "Task Planner",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 35.sp,
            )
            // Heading for task list
            Text(
                text = "Your List of Tasks:",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Task list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .padding(bottom = 60.dp)
            ) {
                // TODO: Implement listing TaskItems using this LazyColumn
                items(items = tasks) { task ->
                    // How each item in myArray is displayed in the LazyColumn {
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(end = 35.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(5.dp) // Add space around each item for visibility
                                    .padding(5.dp) // Add space around each item for visibility
                                    .fillMaxWidth()
                            ) {
                                Text(text = task.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 15.dp))
                                Text(
                                    text = task.description,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Checkbox(
                                checked = task.isSelected,
                                onCheckedChange = null,
                                modifier = Modifier.padding(end=5.dp))
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
}