package com.waaadedev.taskmanager.data

import androidx.room.*
import com.waaadedev.taskmanager.data.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY isDone ASC")
    fun getAllTasks() : List<Task>

    @Query("SELECT * FROM task WHERE isDone == 1")
    fun getCompletedTasks() : List<Task>

    @Query("SELECT * FROM task WHERE isDone == 0")
    fun getCurrentTasks() : List<Task>

    @Insert
    fun insert(vararg task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(vararg task: Task)
}