package edu.towson.cosc435.alexander.planner.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LandscapeView(selectedTask: String?, content: @Composable () -> Unit) {
    Row(
    ) {
        Card(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterVertically)
                .padding(start=16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                if(selectedTask != null) {
                    Text(selectedTask, style= MaterialTheme.typography.titleLarge)
                }
            }
        }
        content()
    }
}