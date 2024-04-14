package edu.towson.cosc435.alexander.planner.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel : ViewModel() {
    private val _selectedDate = MutableLiveData<CalendarDate>()
    val selectedDate: LiveData<CalendarDate> = _selectedDate

    fun setSelectedDate(date: CalendarDate) {
        _selectedDate.value = date
    }
}