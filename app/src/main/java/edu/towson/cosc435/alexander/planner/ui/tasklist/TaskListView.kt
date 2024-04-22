package edu.towson.cosc435.alexander.planner.ui.tasklist

import android.content.res.Configuration
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.AddTaskButton
import edu.towson.cosc435.alexander.planner.ui.LandscapeView
import edu.towson.cosc435.alexander.planner.ui.TaskRow



@OptIn(ExperimentalFoundationApi::class)
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
    }
    Column(
//        modifier = Modifier.alpha(if(deleting) 0.2f else 1.0f)
    ) {
        val content: @Composable () -> Unit = {
            LazyColumn {
                items(tasks) { task ->
                    TaskRow(task, {}, {}, {})
                }
            }
        }
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            content()
        } else {
            LandscapeView(selectedTask = selectedTask?.title) {
                content()
            }
        }
    }
        AddTaskButton(
            onClick = onAddTask,
            modifier = Modifier.padding(16.dp)
        )

}