package edu.towson.cosc435.alexander.planner.data

import edu.towson.cosc435.alexander.planner.data.database.Task
import java.time.LocalDate

interface ITaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun deleteTask(task: Task)
    suspend fun addTask(task: Task)
    suspend fun getTasksForDate(date: LocalDate): List<Task>
}