package edu.towson.cosc435.alexander.planner.ui.newtask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.alexander.planner.data.model.Task

class NewTaskViewModel : ViewModel() {

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> = _title
    private val _description: MutableState<String> = mutableStateOf("")
    val description: State<String> = _description
    private val _taskDate: MutableState<String> = mutableStateOf("")
    val taskDate: State<String> = _taskDate
    private val _taskTime: MutableState<String> = mutableStateOf("")
    val taskTime: State<String> = _taskTime
    private val _isSelected: MutableState<Boolean> = mutableStateOf(false)
    val isSelected: State<Boolean> = _isSelected


    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setDate(taskDate: String) {
        _taskDate.value = taskDate
    }

    fun setTime(taskTime: String){
        _taskTime.value = taskTime
    }

    fun setSelected(isSelected: Boolean){
        _isSelected.value = isSelected
    }


    /**
     * this function validates the input fields
     * @return a Task object (view model)
     */
    fun validateTask(): Task {

        if (title.value.isEmpty()) {
            throw Exception("The task needs a title")
        }
        if (description.value.isEmpty()) {
            throw Exception("The task needs a description")
        }
        if (taskDate.value.isEmpty()) {
            throw Exception("The task needs a date")
        }
        if (taskTime.value.isEmpty()) {
            throw Exception("The task needs you to set a time")
        }

        return Task("", title.value, description.value, taskDate.value, taskTime.value, isSelected.value)
    }
}