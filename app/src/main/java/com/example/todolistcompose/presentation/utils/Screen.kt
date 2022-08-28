package com.example.todolistcompose.presentation.utils

sealed class Screen(val route: String) {
    object TodosScreen : Screen(route = "todos")
    object AddTodosScreen : Screen(route = "add-todos")
    object TodoDetailsScreen : Screen(route = "todo-details")
}