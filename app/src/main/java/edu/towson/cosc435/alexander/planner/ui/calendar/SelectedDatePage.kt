package edu.towson.cosc435.alexander.planner.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.CalendarDate
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectedDatePage(
    date: CalendarDate,
    tasks: List<Task>
) {
    val day = date.day.toString()
    val month = Month.of(date.month).getDisplayName(TextStyle.FULL, Locale.getDefault())
    val year = date.year.toString()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$month $day, $year",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 24.sp
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .padding(bottom = 75.dp)
        ) {
            // TODO: Implement listing TaskItems using this LazyColumn
            items(items = tasks) { item ->
                // How each item in myArray is displayed in the LazyColumn
                Card(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row() {
                        Column(
                            modifier = Modifier
                                .padding(5.dp) // Add space around each item for visibility
                                .fillMaxWidth()
                        ) {
                            Text(text = item.title, style = MaterialTheme.typography.titleLarge)
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(onClick = {
                                //                          viewModel.toggleDeleteModal()
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
