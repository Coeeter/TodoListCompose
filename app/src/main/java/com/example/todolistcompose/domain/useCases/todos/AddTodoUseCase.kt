package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.exceptions.InvalidTodoException
import com.example.todolistcompose.domain.repos.TodoRepo
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    operator fun invoke(
        todo: TodoModel
    ) {
        if (todo.task.isEmpty())
            throw InvalidTodoException("Task of todo cannot be empty")
        if (todo.timeToStartTask > todo.timeToComplete)
            throw InvalidTodoException("Time to complete of todo cannot be before the start date")
        todoRepo.addTodo(todo)
    }
}