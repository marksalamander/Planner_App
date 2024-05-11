package edu.towson.cosc435.alexander.planner.ui.tasklist

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.towson.cosc435.alexander.planner.data.database.Task
import edu.towson.cosc435.alexander.planner.data.database.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class TaskListViewModel (app: Application) : AndroidViewModel(app) {
    private val _tasks: MutableState<List<Task>> = mutableStateOf(emptyList())
    val tasks: State<List<Task>> = _tasks

    private val _selectedTasks: MutableState<Set<Task>> = mutableStateOf(emptySet())
    val selectedTasks: State<List<Task>> = derivedStateOf {
        _selectedTasks.value.toList()
    }

    private val repository : TaskRepository = TaskRepository(getApplication())

    val anyItemsSelected: Boolean
        get() = _selectedTasks.value.isNotEmpty()

    init {
        viewModelScope.launch(Dispatchers.IO) {
                _tasks.value = repository.getTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun toggleSelected(task: Task) {
        if (task in _selectedTasks.value) {
            _selectedTasks.value -= task
        } else {
            _selectedTasks.value += task
        }
    }

    fun clearSelected() {
        _selectedTasks.value = emptySet()
    }
}