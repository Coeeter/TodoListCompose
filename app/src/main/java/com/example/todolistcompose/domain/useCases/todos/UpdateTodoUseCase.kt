package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.exceptions.InvalidTodoException
import com.example.todolistcompose.domain.repos.TodoRepo
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    operator fun invoke(todo: TodoModel) {
        if (todo.task.isEmpty())
            throw InvalidTodoException("Task of todo cannot be empty")
        todo.timeUpdated = System.currentTimeMillis()
        todoRepo.updateTodo(todo)
    }
}