package edu.towson.cosc435.alexander.planner.ui.calendar

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.towson.cosc435.alexander.planner.data.database.Task
import edu.towson.cosc435.alexander.planner.data.database.TaskRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CalendarViewModel (app: Application) : AndroidViewModel(app) {
    private val _selectedDate = MutableLiveData<LocalDate>()
    val selectedDate: LiveData<LocalDate> = _selectedDate
    private val _tasks: MutableState<List<Task>> = mutableStateOf(emptyList())
    val tasks: State<List<Task>> = _tasks
    private val _dateTasks: MutableState<List<Task>> = mutableStateOf(emptyList())
    val dateTasks: State<List<Task>> = _dateTasks
    private val repository : TaskRepository = TaskRepository(getApplication())

    init {
        _selectedDate.value = LocalDate.now()
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _tasks.value = repository.getTasks()
        }
    }

    fun loadSelectedDateTasks() {
        val selectedDateForLoading = selectedDate.value ?: LocalDate.now()
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _dateTasks.value = repository.getTasksForDate(selectedDateForLoading)
        }
    }
}

val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}