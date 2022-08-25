package com.example.todolistcompose.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.useCases.todos.GetTodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodoScreenViewModel @Inject constructor(
    private val getTodoListUseCase: GetTodoListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<List<TodoModel>>(emptyList())
    val state = _state.asStateFlow()

    init {
        getTodoList()
    }

    private fun getTodoList() {
        getTodoListUseCase().onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }
}