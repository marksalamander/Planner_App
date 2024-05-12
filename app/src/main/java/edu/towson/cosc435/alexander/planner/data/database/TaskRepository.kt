package edu.towson.cosc435.alexander.planner.data.database

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435.alexander.planner.data.ITaskRepository
import edu.towson.cosc435.alexander.planner.data.PlannerDatabase
import edu.towson.cosc435.alexander.planner.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TaskRepository(app: Application) :ITaskRepository {
//    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    private var _tasks = listOf<Task>()
    // create the database
    private val db: PlannerDatabase = Room.databaseBuilder(app, PlannerDatabase::class.java, "tasks.db")
        .fallbackToDestructiveMigration()
        .build()

    override suspend fun upsertTask(task: Task) {
        withContext(Dispatchers.IO) {
            db.taskDao().upsertTask(task)
        }
    }

    override suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            db.taskDao().getTasks()
        }
    }

    override suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            db.taskDao().deleteTask(task)
        }
    }

    override suspend fun getTasksForDate(date: LocalDate): List<Task> {
        return withContext(Dispatchers.IO) {
            db.taskDao().getTasksForDate(date)
        }
    }

    override suspend fun toggleSelected(task: Task) {
        withContext(Dispatchers.IO) {
            _tasks = _tasks.map { t ->
                if (t.id == task.id) {
                    t.copy(isSelected = !t.isSelected)
                } else {
                    t
                }
            }
        }
    }
}