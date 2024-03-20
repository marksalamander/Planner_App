package edu.towson.cosc435.alexander.planner.ui.newtask

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NewTaskViewModel : ViewModel() {

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> = _title
    private val _description: MutableState<String> = mutableStateOf("")
    val description: State<String> = _description
    private val _taskDate: MutableState<String> = mutableStateOf("")
    val taskDate: State<String> = _taskDate
    private val _taskTime: MutableState<String> = mutableStateOf("")
    val taskTime: State<String> = _taskTime
    private val _isAlarmSet: MutableState<Boolean> = mutableStateOf(false)
    val isAlarmSet: State<Boolean> = _isAlarmSet


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

    fun setAlarm(isAlarmSet: Boolean){
        _isAlarmSet.value = isAlarmSet
    }


}