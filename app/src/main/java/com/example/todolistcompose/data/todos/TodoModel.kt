package com.example.todolistcompose.data.todos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoModel(
    var task: String,
    var isCompleted: Boolean,
    var timeToStartTask: Long,
    var timeToComplete: Long,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)