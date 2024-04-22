
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
import edu.towson.cosc435.alexander.planner.data.model.CalendarDate
import edu.towson.cosc435.alexander.planner.data.model.Task
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    tasks: List<Task>,
    onDateSelected: (CalendarDate, List<Task>) -> Unit
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var calendarGrid by remember(currentMonth) { mutableStateOf(generateCalendarGrid(currentMonth)) }
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
                        calendarGrid = generateCalendarGrid(currentMonth)
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
                        calendarGrid = generateCalendarGrid(currentMonth)
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

        val datesWithTasks = getDatesWithTasks(tasks)

        // Generates dates for each cell within the calendar grid
        LazyColumn {
            itemsIndexed(calendarGrid) { _, row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (date in row) {
                        DayCell(date = date, tasks = datesWithTasks) {selectedDate ->
                            onDateClick(date = selectedDate, tasks = tasks) {date, currentTasks ->
                                onDateSelected(date, currentTasks)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Parses Task.taskDate string, converting it to type CalendarDate
fun parseDateStringToDate(dateString: String): CalendarDate {
    val dateParts = dateString.split("-") // Assuming the date string is in "day-month-year" format
    val day = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val year = dateParts[2].toInt()
    return CalendarDate(day, month, year)
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDatesWithTasks(tasks: List<Task>): List<CalendarDate> {
    return tasks.map { parseDateStringToDate(it.taskDate) }
}

// Generates the calendar grid
@RequiresApi(Build.VERSION_CODES.O)
fun generateCalendarGrid(month: YearMonth): List<List<CalendarDate>> {
    val daysInMonth = month.lengthOfMonth()
    val firstDayOfMonth = month.atDay(1).dayOfWeek // Gets the first day of the month
    val startingDayOffset = (firstDayOfMonth.value - DayOfWeek.SUNDAY.value + 7) % 7

    val calendarGrid = mutableListOf<MutableList<CalendarDate>>()
    var currentRow = mutableListOf<CalendarDate>()

    // Adds empty cells for days before the start of the month
    for (i in 1..startingDayOffset) {
        currentRow.add(CalendarDate(day = -1, month = -1, year = -1))
    }

    var currentDay = 1
    // Adds days of the month to the grid
    while (currentDay <= daysInMonth) {
        if (currentRow.size == 7) {
            calendarGrid.add(currentRow)
            currentRow = mutableListOf()
        } else {
            currentRow.add(
                CalendarDate(
                    day = currentDay,
                    month = month.monthValue,
                    year = month.year
                )
            )
            currentDay++
        }
    }

    // Adds remaining empty cells to complete the last row
    while (currentRow.size < 7) {
        currentRow.add(CalendarDate(day = -1, month = -1, year = -1))
    }

    // Adds the last row to the grid
    calendarGrid.add(currentRow)

    return calendarGrid
}

// Returns the date that was clicked, as well as tasks that occur on the date
fun onDateClick (
    date: CalendarDate,
    tasks: List<Task>,
    dateSelected: (CalendarDate, List<Task>) -> Unit
) {
    val currentTasks = mutableListOf<Task>()
    for(task in tasks) {
        if(parseDateStringToDate(task.taskDate) == date) {
            currentTasks.add(task)
        }
    }
    dateSelected(date, currentTasks)
}

// Draws calender cell for each day within the month
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(
    date: CalendarDate,
    tasks: List<CalendarDate>,
    onItemClick: (CalendarDate) -> Unit
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
                    isToday -> theme.primary.copy(alpha = 0.45f) // Change color for selected date
                    hasTasks -> Color.Red.copy(alpha = 0.45f) // Change color for date with tasks
                    else -> Color.Transparent
                }
            )
            .clickable { onItemClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            // Displays only valid dates, else displays empty cells to fill row
            text = if (date.day != -1) date.day.toString() else "",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
    }
}

// Function that determines the current day, returns boolean
@RequiresApi(Build.VERSION_CODES.O)
fun isToday(date: CalendarDate): Boolean {
    val today = LocalDate.now()
    return date.year == today.year && date.month == today.monthValue && date.day == today.dayOfMonth
}
