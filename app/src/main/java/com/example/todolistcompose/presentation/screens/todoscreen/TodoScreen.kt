package com.example.todolistcompose.presentation.screens.todoscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistcompose.presentation.screens.todoscreen.components.NoTodos
import com.example.todolistcompose.presentation.screens.todoscreen.components.TodoCard
import com.example.todolistcompose.presentation.utils.Screen
import com.example.todolistcompose.presentation.viewmodels.TodoScreenViewModel

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    todoViewModel: TodoScreenViewModel = hiltViewModel()
) {
    val todoList by todoViewModel.state.collectAsState()
    var isMenuOpen by remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Todos") },
                backgroundColor = MaterialTheme.colors.primary,
                actions = {
                    IconButton(onClick = { isMenuOpen = !isMenuOpen }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = isMenuOpen,
                        onDismissRequest = { isMenuOpen = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            todoViewModel.deleteAllCompleted()
                            isMenuOpen = false
                        }) {
                            Text(text = "Delete all completed Todos")
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddTodosScreen.route)
            }) {
                Icon(Icons.Filled.Add, "Add Todo")
            }
        }
    ) {
        if (todoList.isNotEmpty())
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(todoList) { todo ->
                    TodoCard(
                        todo = todo,
                        navController = navController,
                        toggleCompleted = {
                            todoViewModel.toggleCompleted(todo)
                        }
                    )
                }
            }
        if (todoList.isEmpty()) NoTodos()
    }
}