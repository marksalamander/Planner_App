package edu.towson.cosc435.alexander.planner.ui.newtask

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme
import java.lang.Exception


@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalComposeApi
@Composable
fun NewTaskView(
    taskModel: NewTaskViewModel = viewModel(),
    onAddTask: (Task) -> Unit
) {
    val ctx = LocalContext.current
    val (titleTf, descriptionTf, dateTf, timeTf, selectedTf) = remember { FocusRequester.createRefs() }

    val newTaskTitle = remember { mutableStateOf("") }
    val newTaskDescription = remember { mutableStateOf("") }
    val newTaskDate = remember { mutableStateOf("") }
    val newTaskTime = remember { mutableStateOf( "") }
    //TODO: Implementation for the NewTaskView

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Task Wizard",
            fontSize = 35.sp,
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.Start)
        )
        Text(
            text = "Task Title",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskTitle.value,
            onValueChange = { newValue: String ->
                newTaskTitle.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Task Description",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskDescription.value,
            onValueChange = { newValue: String ->
                newTaskDescription.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Date of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        Text(
            text = "(DD-MM-YYYY, ex. 15-4-2022)"
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskDate.value,
            onValueChange = { newValue: String ->
                newTaskDate.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Time of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskTime.value,
            onValueChange = { newValue: String ->
                newTaskTime.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        FloatingActionButton(onClick = {
//            viewModel.toggleDeleteModal()
        }) {
            Icon(Icons.Default.Done, contentDescription = "Add Task")
        }
    }
}
@OptIn(ExperimentalComposeApi::class)
@Composable
fun DefaultPreview() {
    PlannerTheme {
        val vm = NewTaskViewModel()
        NewTaskView(vm, {})
    }
}

