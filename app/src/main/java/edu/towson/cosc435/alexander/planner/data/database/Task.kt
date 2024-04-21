package edu.towson.cosc435.alexander.planner.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val taskDate: String,
    val taskTime: String,
    val isAlarmSet: Boolean
    // Add more fields if needed
)