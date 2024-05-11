package edu.towson.cosc435.alexander.planner.data.database

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435.alexander.planner.data.ITaskRepository
import edu.towson.cosc435.alexander.planner.data.PlannerDatabase
import java.time.LocalDate

class TaskRepository(app: Application) :ITaskRepository {
//    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
//
//    suspend fun upsert(task: Task) {
//        taskDao.upsertTask(task)
//    }
//
//    suspend fun delete(task: Task) {
//        taskDao.deleteTask(task)
//    }
//
//    fun getTasksForDate(date: LocalDate): Flow<List<Task>> {
//        return taskDao.getTasksForDate(date)
//    }
    private val db: PlannerDatabase

    init {
        // create the database
        db = Room.databaseBuilder(app, PlannerDatabase::class.java, "tasks.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    override suspend fun getTasks(): List<Task> {
        return db.taskDao().getAllTasks()
    }

    override suspend fun deleteTask(task: Task) {
        db.taskDao().deleteTask(task)
    }

    override suspend fun addTask(task: Task) {
        db.taskDao().upsertTask(task)
    }

    override suspend fun getTasksForDate(date: LocalDate): List<Task> {
        return db.taskDao().getTasksForDate(date)
    }
}