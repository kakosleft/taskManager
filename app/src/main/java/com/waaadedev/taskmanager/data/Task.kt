package com.waaadedev.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var description: String,
    var text: String,
    var date: Long,
    var isDone: Boolean
)