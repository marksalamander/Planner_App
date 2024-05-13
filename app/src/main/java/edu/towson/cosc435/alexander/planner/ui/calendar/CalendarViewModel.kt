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
import edu.towson.cosc435.alexander.planner.data.database.TaskRepository
import edu.towson.cosc435.alexander.planner.data.model.Task
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

    private val _repository : TaskRepository = TaskRepository(getApplication())
    private val _selected: MutableState<Task?> = mutableStateOf(null)

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _tasks.value = _repository.getTasks()
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            _repository.deleteTask(task)
            _tasks.value = _repository.getTasks()
        }
    }

    fun toggleSelected(task: Task) {
        viewModelScope.launch {
            _repository.toggleSelected(task)
            _tasks.value = _repository.getTasks()
        }
    }
    fun selectTask(task: Task) {
        _selected.value = task
    }

    fun loadSelectedDateTasks() {
        val selectedDateForLoading = selectedDate.value ?: LocalDate.now()
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _dateTasks.value = _repository.getTasksForDate(selectedDateForLoading)
        }
    }
}

val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}