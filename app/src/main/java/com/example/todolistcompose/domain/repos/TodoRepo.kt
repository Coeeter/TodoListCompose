package com.example.todolistcompose.domain.repos

import com.example.todolistcompose.data.todos.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    fun getTodoList(): Flow<List<TodoModel>>
    fun getTodoById(id: Int): TodoModel?
    fun addTodo(todoModel: TodoModel)
    fun deleteTodo(todoModel: TodoModel)
    fun deleteAllCompletedTodos()
}