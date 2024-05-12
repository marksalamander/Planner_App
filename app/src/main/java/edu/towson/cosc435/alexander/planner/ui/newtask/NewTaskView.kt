package edu.towson.cosc435.alexander.planner.ui.newtask

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.derivedStateOf
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
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


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
    var dateSet by remember { mutableStateOf(false) }
    var timeSet by remember { mutableStateOf(false) }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd, yyyy")
                .format(newTaskDate)
        }
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(newTaskTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()
    val context = LocalContext.current
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            newTaskDate = it
            dateSet = true
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a date",
        ) {
            newTaskTime = it
            timeSet = true
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            top = 75.dp
        )
    ) {
        // Task Title
        Text(
            text = "Task Title",
            fontSize = 20.sp,
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
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(
            value = newTaskDescription,
            onValueChange = { setNewTaskDescription(it) },
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Date and Time",
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                dateDialogState.show()
            }) {
                Text(
                    text="Date Picker",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }

            Button(onClick = {
                timeDialogState.show()
            }) {
                Text(
                    text="Time Picker",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (dateSet) {
                Text(
                    text = formattedDate,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(15.dp)
                )
            }
            if (timeSet) {
                Text(
                    text = formattedTime,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Add Task Button
        FloatingActionButton(
            onClick = {
                try {
                    val task = taskModel.validateTask(
                        newTaskTitle,
                        newTaskDescription,
                        newTaskDate,
                        newTaskTime,
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