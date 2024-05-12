package edu.towson.cosc435.alexander.planner.data.database

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435.alexander.planner.data.ITaskRepository
import edu.towson.cosc435.alexander.planner.data.PlannerDatabase
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate

class TaskRepository(app: Application) :ITaskRepository {
//    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()
//
    private var _tasks = listOf<Task>()
    // create the database
    private val db: PlannerDatabase = Room.databaseBuilder(app, PlannerDatabase::class.java, "tasks.db")
        .fallbackToDestructiveMigration()
        .build()

    override suspend fun upsertTask(task: Task) {
        db.taskDao().upsertTask(task)
    }

    override suspend fun getTasks(): List<Task> {
        return db.taskDao().getTasks()
    }

    override suspend fun deleteTask(task: Task) {
        db.taskDao().deleteTask(task)
    }

    override suspend fun getTasksForDate(date: LocalDate): List<Task> {
        return db.taskDao().getTasksForDate(date)
    }

    override suspend fun toggleSelected(task: Task) {
        _tasks = _tasks.map { t ->
            if(t.id == task.id) {
                t.copy(isSelected = !t.isSelected)
            } else {
                t
            }
        }
    }
}