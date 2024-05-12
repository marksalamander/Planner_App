package edu.towson.cosc435.alexander.planner.ui.tasklist

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.towson.cosc435.alexander.planner.data.database.TaskRepository
import edu.towson.cosc435.alexander.planner.data.model.Task
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListViewModel (app: Application) : AndroidViewModel(app) {
    private val _tasks: MutableState<List<Task>> = mutableStateOf(emptyList())
    val tasks: State<List<Task>> = _tasks

    private val _selected: MutableState<Task?>
    val selectedTask: State<Task?>

    private val _selectedTasks: MutableState<List<Task>> = mutableStateOf(emptyList())
    val selectedTasks: State<List<Task>> = derivedStateOf {
        _selectedTasks.value.toList()
    }

    val anyTasksSelected: Boolean
        get() = _tasks.value.any { it.isSelected }

    private val _repository : TaskRepository = TaskRepository(getApplication())

    private val _deleting: MutableState<Boolean>
    val deleting: State<Boolean>

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                _tasks.value = _repository.getTasks()
        }

        _selected = mutableStateOf(null)
        selectedTask = _selected

        _deleting = mutableStateOf(false)
        deleting = _deleting

    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            _repository.upsertTask(task)
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

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO + edu.towson.cosc435.alexander.planner.ui.calendar.coroutineExceptionHandler) {
            _tasks.value = _repository.getTasks()
        }
    }

    fun deleteSelectedTasks() {
        viewModelScope.launch {
            _selectedTasks.value.forEach { task ->
                _repository.deleteTask(task)
            }

            _tasks.value = _repository.getTasks()
            _selectedTasks.value = _tasks.value.filter { it.isSelected }
        }
    }

    fun selectTask(task: Task) {
        _selected.value = task
    }

    fun toggleDeleteModal() {
        _deleting.value = !_deleting.value
    }
}
val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
    throwable.printStackTrace()
}
