package com.waaadedev.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    var id: Int,
    var description: String,
    var text: String,
    var date: String,
    var isDone: Boolean
)