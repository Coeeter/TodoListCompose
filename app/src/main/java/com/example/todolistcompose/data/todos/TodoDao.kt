package com.example.todolistcompose.data.todos

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getTodoList(): Flow<List<TodoModel>>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): TodoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTodo(todoModel: TodoModel)

    @Delete
    fun deleteTodo(todoModel: TodoModel)

    @Query("DELETE FROM todos WHERE isCompleted = 1")
    fun deleteCompletedTodos()
}