package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.domain.repos.TodoRepo
import javax.inject.Inject

class GetTodoByIdUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    operator fun invoke(id: Int) = todoRepo.getTodoById(id)
}