package edu.towson.cosc435.alexander.planner.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalTime,
    val alarmSet: Boolean
)
