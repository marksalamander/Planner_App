package edu.towson.cosc435.alexander.planner.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.TaskRow

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
// Lists the tasks that are on a specific date (selected by the user) ~Mark
fun SelectedDatePage(
    tasks: State<List<Task>>,
    onDelete: (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onSelectItem: (Task) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
                    .padding(top=75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .padding(bottom = 75.dp)
        ) {
            items(items = tasks.value) { task ->
                TaskRow(task, onDelete, onToggle, onSelectItem)
            }
        }
    }
}

