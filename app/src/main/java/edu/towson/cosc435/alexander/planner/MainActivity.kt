package edu.towson.cosc435.alexander.planner

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import edu.towson.cosc435.alexander.planner.ui.MainScreen
import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarViewModel
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme

// TODO: Import navigation (for some reason I can't find the correct statements)

class MainActivity : ComponentActivity() {
    private val calendarViewModel: CalendarViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen(viewModel = calendarViewModel)
                }
            }
        }
    }
}





