package edu.towson.cosc435.alexander.planner.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectedDatePage(
    date: LocalDate,
    tasks: State<List<Task>>,
    onToggle: (Task) -> Unit,
) {
    val day = date.dayOfMonth
    val month = Month.of(date.monthValue).getDisplayName(TextStyle.FULL, Locale.getDefault())
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
            items(items = tasks.value) { item ->
                // How each item in myArray is displayed in the LazyColumn
                Card(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp, end = 30.dp)
                            .combinedClickable (
                                onLongClick = {
                                    //onDelete(task)
                                }
                            ){
                                //onSelectItem(task)
                            }
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        onToggle(item)
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(5.dp) // Add space around each item for visibility
                                .padding(5.dp) // Add space around each item for visibility
                                .fillMaxWidth()
                        ) {
                            Text(text = item.title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 15.dp))
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Checkbox(checked = item.isSelected, onCheckedChange = null, modifier = Modifier.padding(end=75.dp))
                    }
                }
            }
        }
    }
}
