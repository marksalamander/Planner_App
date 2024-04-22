package edu.towson.cosc435.alexander.planner.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.towson.cosc435.alexander.planner.data.model.CalendarDate
import edu.towson.cosc435.alexander.planner.data.model.Task

class CalendarViewModel : ViewModel() {
    private val _selectedDate = MutableLiveData<CalendarDate>()
    val selectedDate: MutableLiveData<CalendarDate> = _selectedDate
    private var _tasks = listOf<Task>()
    val tasks: List<Task> get() = _tasks

    fun setSelectedDate(date: CalendarDate, currentTasks: List<Task>) {
        _selectedDate.value = date
        _tasks = currentTasks
    }
}