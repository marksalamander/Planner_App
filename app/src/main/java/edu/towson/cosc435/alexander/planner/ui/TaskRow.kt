package edu.towson.cosc435.alexander.planner.ui

import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import edu.towson.cosc435.alexander.planner.data.model.Task


@ExperimentalFoundationApi
@Composable
fun TaskRow(
    task: Task,
    onDelete: (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onSelectItem: (Task) -> Unit
) {
    Row(
        modifier = Modifier
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.weight(1.5f)
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Title:", modifier = Modifier.weight(1.0f))
                Text(
                    task.title,
                    modifier = Modifier.weight(2.0f),
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Date:", modifier = Modifier.weight(1.0f))
                Text(task.taskDate.toString(), modifier = Modifier.weight(2.0f))
            }
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Time:", modifier = Modifier.weight(1.0f))
                Text(task.taskTime.toString(), modifier = Modifier.weight(2.0f))
            }
        }
    }
}
