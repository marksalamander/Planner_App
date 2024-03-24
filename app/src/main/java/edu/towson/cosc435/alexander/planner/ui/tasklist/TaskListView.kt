package edu.towson.cosc435.alexander.planner.ui.tasklist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.AddTaskButton
import edu.towson.cosc435.alexander.planner.ui.TaskRow



@ExperimentalComposeApi
@Composable
fun TaskListView (

    //TODO: Finish implementation
    tasks: List<Task>,
    selectedTask: Task?,
    onDelete: (Task) -> Unit,
    onFilter: (String) -> Unit,
    onSelectTask: (Task) -> Unit,
    onAddTask: () -> Unit,

) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column() {
            //SearchBar( onFilter = onFilter )

            LazyColumn {
                items(tasks) {task ->

                    //TODO: Figure this out
                    //TaskRow(task)
                }
            }
        }
        AddTaskButton(
            onClick = onAddTask,
            modifier = Modifier.padding(16.dp).align(Alignment.BottomEnd)
        )
    }
}