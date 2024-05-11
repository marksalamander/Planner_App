package edu.towson.cosc435.alexander.planner.ui.tasklist

import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import edu.towson.cosc435.alexander.planner.R
import edu.towson.cosc435.alexander.planner.data.model.OldTask
import edu.towson.cosc435.alexander.planner.ui.AddTaskButton
import edu.towson.cosc435.alexander.planner.ui.LandscapeView
import edu.towson.cosc435.alexander.planner.ui.TaskRow



@OptIn(ExperimentalFoundationApi::class)
@ExperimentalComposeApi
@Composable
fun TaskListView (

    tasks: List<OldTask>,
    selectedTask: OldTask?,
    onDelete: (OldTask) -> Unit,
    onFilter: (String) -> Unit,
    onSelectTask: (OldTask) -> Unit,
    onAddTask: () -> Unit,

    ) {
    val context = LocalContext.current

    // Adam: Check for notification permission
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

    // Adam: Send task reminder notification
    fun remindAboutTask(taskId: Int, taskTitle: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "1")
            .setContentTitle("Task due soon!")
            .setContentText(taskTitle)
            // TODO: Replace R.drawable.ic_launcher_foreground with app icon
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(1, notification)
    }

    // TODO: Uncomment after implementing database
//    // Adam: If any task is due tomorrow, send notification
//    for (task in tasks) {
//        if (/* Task date and time is tomorrow */) {
//            remindAboutTask(task.id, task.title)
//        }
//    }

    Box(
        contentAlignment = Alignment.Center,
    ) {
    }
    Column(
//        modifier = Modifier.alpha(if(deleting) 0.2f else 1.0f)
    ) {
        val content: @Composable () -> Unit = {
            LazyColumn {
                items(tasks) { task ->
                    TaskRow(task, {}, {}, {})
                }
            }
        }
        if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
            content()
        } else {
            LandscapeView(selectedTask = selectedTask?.title) {
                content()
            }
        }
    }
        AddTaskButton(
            onClick = onAddTask,
            modifier = Modifier.padding(16.dp)
        )
}