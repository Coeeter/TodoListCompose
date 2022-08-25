package com.example.todolistcompose.domain.useCases.todos

import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.exceptions.TodoNotFoundException
import com.example.todolistcompose.domain.repos.TodoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoByIdUseCase @Inject constructor(
    private val todoRepo: TodoRepo
) {
    @Throws(TodoNotFoundException::class)
    operator fun invoke(id: Int): Flow<TodoModel> {
        return flow {
            val todo = todoRepo.getTodoById(id)
                ?: throw TodoNotFoundException("Todo with $id not found")
            emit(todo)
        }
    }
}