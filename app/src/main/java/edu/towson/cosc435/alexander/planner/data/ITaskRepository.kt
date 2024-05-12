package edu.towson.cosc435.alexander.planner.data

import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate

interface ITaskRepository {

    suspend fun updateTask(task: Task)
    suspend fun getTasks(): List<Task>
    suspend fun deleteTask(task: Task)
    suspend fun addTask(task: Task)
    suspend fun getTasksForDate(date: LocalDate): List<Task>
    suspend fun toggleSelected(task: Task)
}