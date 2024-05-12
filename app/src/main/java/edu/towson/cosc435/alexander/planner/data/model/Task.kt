package edu.towson.cosc435.alexander.planner.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "tasks")
data class Task (

    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val taskDate: LocalDate,
    val taskTime: LocalTime,
    val isSelected: Boolean,
    val isAlarmSet: Boolean,
    ){
}