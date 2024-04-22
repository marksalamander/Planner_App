package edu.towson.cosc435.alexander.planner.ui.tasklist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.alexander.planner.data.model.Task
import edu.towson.cosc435.alexander.planner.data.TaskListRepository
import edu.towson.cosc435.alexander.planner.data.impl.TaskRepository

class TaskListViewModel : ViewModel() {
    //TODO: Not sure we need this, but I added this as a placeholder
    private val _tasks: MutableState<List<Task>> = mutableStateOf(listOf())
    val songs: State<List<Task>> = _tasks

    private val _selected: MutableState<Task?>
    val selectedTask: State<Task?>

    private val _repository: TaskListRepository = TaskRepository()

//    val anyItemsSelected: Boolean
//        get() = _tasks.value.any { it.isSelected }


    init {
        _tasks.value = _repository.getTasks()
        _selected = mutableStateOf(null)
        selectedTask = _selected
    }

    fun addTask(song: Task) {
        _repository.addTask(song)
        _tasks.value = _repository.getTasks()
    }

    fun deleteTask(song: Task) {
        _repository.deleteTask(song)
        _tasks.value = _repository.getTasks()
    }

}