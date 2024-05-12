package edu.towson.cosc435.alexander.planner.data.model

data class OldTask (
    val id: String,
    val title: String,
    val description: String,
    val taskDate: String,
    val taskTime: String,
    val isSelected: Boolean
    //val isAlarmSet: Boolean
    ){
}