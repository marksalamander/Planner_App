package edu.towson.cosc435.alexander.planner.ui.newtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.towson.cosc435.alexander.planner.data.model.Task


@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalComposeApi
@Composable
fun NewTaskView(
    taskModel: NewTaskViewModel = viewModel(),
    onAddTask: (Task) -> Unit
) {
    val ctx = LocalContext.current
    val (titleTf, descriptionTf, dateTf) = remember { FocusRequester.createRefs() }

    //TODO: Implementation for the NewTaskView

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "New Title",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
            .focusRequester(titleTf)
        )
//        OutlinedTextField(
//            value = taskModel.title.value,
//            onValueChange = taskModel::setName,
//            placeholder = {
//                Text("Name")
//            },
//            label = {
//                Text("Name")
//            },
//            singleLine = true,
//            modifier = Modifier
//                .padding(16.dp),
//            //.focusRequester(nameTf),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Next,
//                keyboardType = KeyboardType.Text
//            ),
//            keyboardActions = KeyboardActions(
//                //onNext = { artistTf.requestFocus() }
//            )
//        )
    }
}

