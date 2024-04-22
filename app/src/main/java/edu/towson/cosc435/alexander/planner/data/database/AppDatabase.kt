package edu.towson.cosc435.alexander.planner.data.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Dao
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}