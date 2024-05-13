package edu.towson.cosc435.alexander.planner.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.TaskRow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
// Lists the tasks that are on a specific date (selected by the user) ~Mark
fun SelectedDatePage(
    date: LiveData<LocalDate>,
    tasks: State<List<Task>>,
    onDelete: (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onSelectItem: (Task) -> Unit,
) {
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd, yyyy")
                .format(date.value)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
                    .padding(top=75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text=formattedDate,
            fontSize = 28.sp,
            modifier = Modifier.padding(10.dp)
        )
        if(tasks.value.isEmpty()) { // Checks if there are any tasks on selected date ~Mark
            Text(
                text="No Tasks",
                fontSize = 24.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
        else {
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
}

