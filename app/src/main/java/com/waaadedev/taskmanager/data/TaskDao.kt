package com.waaadedev.taskmanager.data

import androidx.room.*
import com.waaadedev.taskmanager.data.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks() : List<Task>

    @Insert
    fun insert(vararg task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(vararg task: Task)
}