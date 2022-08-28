package com.example.todolistcompose.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.domain.useCases.todos.GetTodoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _todo = MutableStateFlow<TodoModel?>(null)
    val todo = _todo.asStateFlow()

    init {
        savedStateHandle.get<Int>("id")?.let {
            getTodoById(it)
        }
    }

    private fun getTodoById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _todo.value = getTodoByIdUseCase(id)
        }
    }
}