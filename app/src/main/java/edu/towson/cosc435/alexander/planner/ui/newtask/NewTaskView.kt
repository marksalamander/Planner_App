package edu.towson.cosc435.alexander.planner.ui.newtask

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.alexander.planner.data.database.Task
import edu.towson.cosc435.alexander.planner.data.model.OldTask
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalComposeApi
@Composable
fun NewTaskView(
    taskModel: NewTaskViewModel = viewModel(),
    onAddTask: (Task) -> Unit
) {
    val (newTaskTitle, setNewTaskTitle) = remember { mutableStateOf("") }
    val (newTaskDescription, setNewTaskDescription) = remember { mutableStateOf("") }
    val (newTaskDateString, setNewTaskDateString) = remember { mutableStateOf("") }
    val (newTaskTimeString, setNewTaskTimeString) = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Task Title
        Text(
            text = "Task Title",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { setNewTaskTitle(it) },
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )

        // Task Description
        Text(
            text = "Task Description",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(
            value = newTaskDescription,
            onValueChange = { setNewTaskDescription(it) },
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )

        // Date of Task
        Text(
            text = "Date of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        Text(
            text = "(DD-MM-YYYY, ex. 15-4-2022)"
        )
        OutlinedTextField(
            value = newTaskDateString,
            onValueChange = { setNewTaskDateString(it) },
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )

        // Time of Task
        Text(
            text = "Time of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(
            value = newTaskTimeString,
            onValueChange = { setNewTaskTimeString(it) },
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )

        // Add Task Button
        FloatingActionButton(
            onClick = {
                try {
                    val task = taskModel.validateTask(
                        newTaskTitle,
                        newTaskDescription,
                        newTaskDateString,
                        newTaskTimeString
                    )
                    taskModel.insertTask(task)
                    onAddTask(task) // Notify the caller that a new task has been added
                } catch (e: Exception) {
                    // Handle validation errors
                    // You can show an error message or log the exception
                    Log.e("NewTaskView", "Validation error: ${e.message}")
                }
            }
        ) {
            Icon(Icons.Default.Done, contentDescription = "Add Task")
        }
    }
}