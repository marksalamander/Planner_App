package edu.towson.cosc435.alexander.planner.data.model

import edu.towson.cosc435.alexander.planner.ui.calendar.CalendarDate

data class Task (
    val id: String,
    val title: String,
    val description: String,
    val taskDate: String,
    val taskTime: String,
    val isAlarmSet: Boolean,
    ){
}

data class T (
    val id: Int,
    val title: String,
    val taskDate: CalendarDate
)