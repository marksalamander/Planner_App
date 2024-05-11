package edu.towson.cosc435.alexander.planner.ui.newtask

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import edu.towson.cosc435.alexander.planner.data.database.Task
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeApi
@Composable
fun NewTaskView(
    taskModel: NewTaskViewModel = viewModel(),
    onAddTask: (Task) -> Unit
) {
    val (newTaskTitle, setNewTaskTitle) = remember { mutableStateOf("") }
    val (newTaskDescription, setNewTaskDescription) = remember { mutableStateOf("") }
    var newTaskDate by remember { mutableStateOf(LocalDate.now()) }
    var newTaskTime by remember { mutableStateOf(LocalTime.now()) }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val context = LocalContext.current
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    context,
                    "Clicked ok",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            newTaskDate = it
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    context,
                    "Clicked ok",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a date",
        ) {
            newTaskTime = it
        }
    }

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
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text("Date Picker")
        }

        // Time of Task
        Text(
            text = "Time of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )

        Button(onClick = {
            timeDialogState.show()
        }) {
            Text("Time Picker")
        }

        // Add Task Button
        FloatingActionButton(
            onClick = {
                try {
                    val task = taskModel.validateTask(
                        newTaskTitle,
                        newTaskDescription,
                        newTaskDate,
                        newTaskTime
                    )
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