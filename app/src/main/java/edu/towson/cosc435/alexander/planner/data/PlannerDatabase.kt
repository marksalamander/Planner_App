package edu.towson.cosc435.alexander.planner.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Upsert
import edu.towson.cosc435.alexander.planner.data.database.Converters
import edu.towson.cosc435.alexander.planner.data.model.Task
import java.time.LocalDate

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Insert
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskDate = :date")
    fun getTasksForDate(date: LocalDate): List<Task>
}

@Database(entities = [Task::class], version = 3, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PlannerDatabase : RoomDatabase() {
    // Gives the database a reference to the DAO
    abstract fun taskDao(): TaskDao
}