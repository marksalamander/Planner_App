package edu.towson.cosc435.alexander.planner.ui.tasklist


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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.ui.TaskRow


// Composable function for the list of tasks displayed on the task list page
@ExperimentalFoundationApi
@Composable
fun TaskListView(
    tasks: State<List<Task>>,
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


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // App title
            Text(
                text = "Task Planner",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 35.sp,
            )
            // Heading for task list
            Text(
                text = "Your List of Tasks:",
            )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .padding(bottom = 59.dp)
                ) {
                    // TODO: Implement listing TaskItems using this LazyColumn
                    items(items = tasks.value) { task ->
                        TaskRow(task, onDelete, onToggle, onSelectItem)

                    }
                }
        }
    }
}