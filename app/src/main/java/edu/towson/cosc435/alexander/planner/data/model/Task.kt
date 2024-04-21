package edu.towson.cosc435.alexander.planner.data.model

data class Task (
    val id: String,
    val title: String,
    val description: String,
    val taskDate: String,
    val taskTime: String,
    val isAlarmSet: Boolean,
    ){
}