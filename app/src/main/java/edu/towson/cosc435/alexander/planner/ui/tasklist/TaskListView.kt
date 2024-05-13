package edu.towson.cosc435.alexander.planner.ui.tasklist

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.LandscapeView
import edu.towson.cosc435.alexander.planner.ui.TaskRow
import kotlinx.coroutines.launch

// Composable function for the list of tasks displayed on the task list page
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalFoundationApi
fun TaskListView(
    vm: TaskListViewModel,
    selectedTask: State<Task?>,
    onDelete: (Task) -> Unit,
    onToggle: (Task) -> Unit,
    onSelectItem: (Task) -> Unit,

    ) {
//    val context = LocalContext.current
//
//    // Adam: Check for notification permission
//    var hasNotificationPermission by remember {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            mutableStateOf(
//                ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) == PackageManager.PERMISSION_GRANTED
//            )
//        } else {
//            mutableStateOf(true)
//        }
//    }
//
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted ->
//            hasNotificationPermission = isGranted
//        }
//    )
//
//    // Adam: Send task reminder notification
//    fun remindAboutTask(taskId: Int, taskTitle: String) {
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification = NotificationCompat.Builder(context, "1")
//            .setContentTitle("Task due soon!")
//            .setContentText(taskTitle)
//            // TODO: Replace R.drawable.ic_launcher_foreground with app icon
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .build()
//        notificationManager.notify(1, notification)
//    }
//
//    // TODO: Uncomment after implementing database
//    // Adam: If any task is due tomorrow, send notification
//    for (task in tasks) {
//        if (/* Task date and time is tomorrow */) {
//            remindAboutTask(task.id, task.title)
//        }
//    }
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        vm.getTasks()
    }
    vm.getTasks()
    var tasks = remember { (vm.tasks) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top=20.dp)
                .fillMaxSize()
        ) {
            // Heading for task list
            Text(
                text = "Your Tasks:",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 24.sp
            )
            val content: @Composable () -> Unit = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                ) {
                    // TODO: Implement listing TaskItems using this LazyColumn
                    items(items = tasks.value) { task ->
                        TaskRow(task, onDelete, onToggle, onSelectItem)

                    }
                }
            }

            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
                content()
            } else {
                LandscapeView(selectedTask = selectedTask.value?.title) {
                    content()
                }
            }
        }
    }
}