
package edu.towson.cosc435.alexander.planner.ui.calendar


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.database.Task
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    viewModel: CalendarViewModel,
    onDateSelected: (LocalDate) -> Unit
) {
    var tasks = remember { (viewModel.tasks) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var calendarGrid by remember(currentMonth) { mutableStateOf(generateCalendarGrid(currentMonth, viewModel)) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            // Button to navigate to previous month
            IconButton(
                onClick = {
                    // Performs update asynchronously in a separate coroutine, allowing smoother transition when changing months
                    coroutineScope.launch {
                        currentMonth = currentMonth.minusMonths(1)
                        calendarGrid = generateCalendarGrid(currentMonth, viewModel)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous Month"
                )
            }

            // Displays current month and year
            Text(
                text = currentMonth.month.toString() + " " + currentMonth.year.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 24.sp
            )

            // Button to navigate to next month
            IconButton(
                onClick = {
                    // Performs update asynchronously in a separate coroutine, allowing smoother transition when changing months
                    coroutineScope.launch {
                        currentMonth = currentMonth.plusMonths(1)
                        calendarGrid = generateCalendarGrid(currentMonth, viewModel)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next Month"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Names of days of the week
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            // List starts days of the week on Sunday
            val days = listOf(DayOfWeek.SUNDAY) + DayOfWeek.values().toList().subList(0, 6)
            for (day in days) {
                Text(
                    text = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        val datesWithTasks = getDatesWithTasks(tasks.value)

        // Generates dates for each cell within the calendar grid
        LazyColumn {
            itemsIndexed(calendarGrid) { _, row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (date in row) {
                        DayCell(date = date, tasks = datesWithTasks) { selectedDate ->
                            viewModel.setSelectedDate(selectedDate)
                            onDateSelected(date)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDatesWithTasks(tasks: List<Task>): List<LocalDate> {
    return tasks.map { it.date }
}


// Generates the calendar grid
@RequiresApi(Build.VERSION_CODES.O)
fun generateCalendarGrid(month: YearMonth, vm: CalendarViewModel): List<List<LocalDate>> {
    vm.getTasks()
    val firstDayOfMonth = month.atDay(1).dayOfWeek // Gets the first day of the month
    val startingDayOffset = (firstDayOfMonth.value - DayOfWeek.SUNDAY.value + 7) % 7

    val calendarGrid = mutableListOf<MutableList<LocalDate>>()
    var currentRow = mutableListOf<LocalDate>()

    // Adds empty cells for days before the start of the month
    for (i in 1..startingDayOffset) {
        currentRow.add(LocalDate.MIN)
    }

    var currentDate = month.atDay(1)
    val daysInMonth = month.lengthOfMonth()

    // Adds days of the month to the grid
    while (currentDate.month == month.month) {
        if (currentRow.size == 7) {
            calendarGrid.add(currentRow)
            currentRow = mutableListOf()
        }
        currentRow.add(currentDate)
        currentDate = currentDate.plusDays(1)
    }

    // Adds remaining empty cells to complete the last row
    while (currentRow.size < 7) {
        currentRow.add(LocalDate.MIN)
    }

    // Adds the last row to the grid
    calendarGrid.add(currentRow)

    return calendarGrid
}

// Draws calender cell for each day within the month
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(
    date: LocalDate,
    tasks: List<LocalDate>,
    onItemClick: (LocalDate) -> Unit
) {
    val isToday = isToday(date)
    val hasTasks = date in tasks
    val theme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(48.dp)
            .clip(CircleShape)
            .background(
                color = when {
                    isToday -> theme.primary.copy(alpha = 0.45f)
                    hasTasks -> Color.Red.copy(alpha = 0.45f) // Change color for selected date
                    else -> Color.Transparent
                }
            )
            .clickable { onItemClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            // Displays only valid dates, else displays empty cells to fill row
            text = if (date != LocalDate.MIN) date.dayOfMonth.toString() else "",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun isToday(date: LocalDate): Boolean {
    val today = LocalDate.now()
    return date.year == today.year && date.monthValue == today.monthValue && date.dayOfMonth == today.dayOfMonth
}