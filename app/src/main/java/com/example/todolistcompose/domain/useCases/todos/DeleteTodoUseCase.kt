package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.repos.TodoRepo
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    operator fun invoke(todoModel: TodoModel) {
        todoRepo.deleteTodo(todoModel)
    }
}