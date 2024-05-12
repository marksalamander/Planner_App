
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435.alexander.planner.data.model.Task

// Composable function for the page to create/read/update/delete tasks/events
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskWizard(
    onTasksCreated: (Task) -> Unit
) {
    // TODO: Replace this placeholder text with actual task wizard implementation
    val newTaskTitle = remember { mutableStateOf("") }
    val newTaskDescription = remember { mutableStateOf("") }
    val newTaskDate = remember { mutableStateOf("") }
    val newTaskTime = remember { mutableStateOf( "") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Task Wizard",
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "Task Title",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskTitle.value,
            onValueChange = { newValue: String ->
                newTaskTitle.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Task Description",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskDescription.value,
            onValueChange = { newValue: String ->
                newTaskDescription.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Date of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        Button(onClick = {

        }) {
            Text("Date Picker")
        }
        OutlinedTextField(

            label = { Text("") },
            value = newTaskDate.value,
            onValueChange = { newValue: String ->
                newTaskDate.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Time of Task",
            fontSize = 15.sp,
            modifier = Modifier.padding(5.dp),
        )
        OutlinedTextField(

            label = { Text("") },
            value = newTaskTime.value,
            onValueChange = { newValue: String ->
                newTaskTime.value = newValue
            },
            placeholder = {
                Text("")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions
                .Default
                .copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Set an alarm?",
                modifier = Modifier.padding(end = 5.dp)
            )

            //TODO: Testing implementation of checkbox
//            Checkbox(
//                checked = taskModel.isAwesome.value,
//                onCheckedChange = taskModel::isAlarmSet
//            )
        }
    }
}
