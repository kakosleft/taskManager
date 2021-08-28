package com.waaadedev.taskmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao?
}