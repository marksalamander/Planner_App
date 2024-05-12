package edu.towson.cosc435.alexander.planner.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.pointer.pointerInput
import edu.towson.cosc435.alexander.planner.data.model.Task


@ExperimentalFoundationApi
@Composable
fun TaskRow(
    task: Task,
    onDelete: (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onSelectItem: (Task) -> Unit
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .combinedClickable(
                    onLongClick = {
                        onDelete(task)
                    }
                ) {
                    onSelectItem(task)
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            onToggle(task)
                        }
                    )
                },
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

            IconButton(onClick = {
                onDelete(task)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

