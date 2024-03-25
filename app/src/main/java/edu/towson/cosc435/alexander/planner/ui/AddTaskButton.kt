package edu.towson.cosc435.alexander.planner.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.material3.FloatingActionButton

@Composable
fun AddTaskButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Filled.Add, contentDescription = null, tint = Color.White)
    }
}