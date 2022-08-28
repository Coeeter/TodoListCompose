package com.example.todolistcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.exceptions.InvalidTodoException
import com.example.todolistcompose.domain.useCases.todos.AddTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class AddTodosScreenViewModel @Inject constructor(
    private val addTodosUseCase: AddTodoUseCase
) : ViewModel() {
    val task = MutableStateFlow("")
    val timeToStart = MutableStateFlow<Long>(0)
    val timeToComplete = MutableStateFlow<Long>(0)

    fun addTodo(): Flow<AddTodoState> {
        return flow {
            emit(AddTodoState.Loading)
            try {
                val todo = TodoModel(
                    task.value,
                    false,
                    timeToStart.value,
                    timeToComplete.value,
                )
                addTodosUseCase(todo)
                emit(AddTodoState.Success)
            } catch (e: InvalidTodoException) {
                emit(AddTodoState.Failure(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    sealed class AddTodoState {
        object Loading : AddTodoState()
        object Success : AddTodoState()
        class Failure(val message: String) : AddTodoState()
    }
}