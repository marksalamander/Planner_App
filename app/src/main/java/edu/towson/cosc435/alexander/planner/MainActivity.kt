package edu.towson.cosc435.alexander.planner

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.tooling.preview.Preview
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.MainScreen
import edu.towson.cosc435.alexander.planner.ui.tasklist.TaskListView
import edu.towson.cosc435.alexander.planner.ui.theme.PlannerTheme
import java.time.LocalDate
import java.time.LocalTime

// TODO: Import navigation (for some reason I can't find the correct statements)
// TODO - Initialize database

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlannerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalComposeApi::class)
//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val tasks = (0..20).map { i ->
//        Task("Task $i", "Important thing to do $i", "This is task, there are many like it, but this one is mine. Ready yourself.",LocalDate.now(), LocalTime.now(), false, false)
//    }
//    PlannerTheme {
//        Surface(color = MaterialTheme.colorScheme.background) {
//            TaskListView(tasks, {}, {}, {}, {})
//        }
//    }
//}





