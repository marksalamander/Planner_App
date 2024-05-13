package edu.towson.cosc435.alexander.planner.ui.newtask

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.towson.cosc435.alexander.planner.data.database.TaskRepository
import edu.towson.cosc435.alexander.planner.data.model.Task
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NewTaskViewModel (app: Application) : AndroidViewModel(app) {
    private val repository : TaskRepository = TaskRepository(getApplication())

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> = _title
    private val _description: MutableState<String> = mutableStateOf("")
    val description: State<String> = _description
    private val _taskDate: MutableState<LocalDate?> = mutableStateOf(null)
    val taskDate: State<LocalDate?> = _taskDate
    private val _taskTime: MutableState<LocalTime?> = mutableStateOf(null)
    val taskTime: State<LocalTime?> = _taskTime
    private val _alarmSet: MutableState<Boolean> = mutableStateOf(false)
    val alarmSet: State<Boolean> = _alarmSet


    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setDate(taskDate: LocalDate?) {
        _taskDate.value = taskDate
    }

    fun setTime(taskTime: LocalTime?){
        _taskTime.value = taskTime
    }

    fun setAlarmSet(alarmSet: Boolean){
        _alarmSet.value = alarmSet
    }


    /**
     * this function validates the input fields
     * @return a Task object (view model)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun validateTask(
        title: String,
        description: String,
        date: LocalDate,
        time: LocalTime
    ): Task {
        val titleValue = title.trim()
        val descriptionValue = description.trim()

        // Check if title is empty
        if (titleValue.isEmpty()) {
            throw IllegalArgumentException("Task title cannot be empty.")
        }

        // Check if description is empty
        if (descriptionValue.isEmpty()) {
            throw IllegalArgumentException("Task description cannot be empty.")
        }

        // Validate and parse date
        val taskDateValue: LocalDate
        try {
            taskDateValue = date
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Invalid date format. Please use DD-MM-YYYY format.")
        }

        // Validate and parse time
        val taskTimeValue: LocalTime
        try {
            taskTimeValue = time
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Invalid time format. Please use HH:mm format.")
        }

        return Task(

            // TODO: Come back to this and put it a proper variable
            id = 0,
            title = titleValue,
            description = descriptionValue,
            taskDate = taskDateValue,
            taskTime = taskTimeValue,
            isSelected = false,
            isAlarmSet = false
        )
    }

    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            repository.upsertTask(task)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseStringToDate(dateString: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.parse(dateString, formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseStringToTime(timeString: String): LocalTime {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalTime.parse(timeString, formatter)
    }
}

val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}