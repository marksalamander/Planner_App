package edu.towson.cosc435.alexander.planner.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskDate = :date")
    suspend fun getTasksForDate(date: LocalDate): List<Task>
}