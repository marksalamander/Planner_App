package edu.towson.cosc435.alexander.planner.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import java.time.LocalDate

//@Dao
//interface TaskDao {
//    @Upsert
//    suspend fun upsertTask(task: Task)
//
//    @Delete
//    suspend fun deleteTask(task: Task)
//
//    @Query("SELECT * FROM tasks")
//    fun getAllTasks(): List<Task>
//
//    @Query("SELECT * FROM tasks WHERE date = :date")
//    fun getTasksForDate(date: LocalDate): List<Task>
//}