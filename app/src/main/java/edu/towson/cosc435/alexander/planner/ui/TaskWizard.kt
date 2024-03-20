package edu.towson.cosc435.alexander.planner.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for the page to create/read/update/delete tasks/events
@Composable
fun TaskWizard() {
    // TODO: Replace this placeholder text with actual task wizard implementation
    val newTaskTitle = remember { mutableStateOf("") }
    val newTaskDescription = remember { mutableStateOf("") }
    val newTaskDate = remember { mutableStateOf("") }
    val newTaskTime = remember { mutableStateOf( "") }

    Text(text = "Task Wizard")
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
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
            modifier = Modifier.padding(15.dp)
        )

    }
}