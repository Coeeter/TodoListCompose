package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.domain.repos.TodoRepo
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    operator fun invoke() = todoRepo.getTodoList()
}