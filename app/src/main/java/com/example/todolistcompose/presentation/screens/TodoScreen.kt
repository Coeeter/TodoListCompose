package com.example.todolistcompose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistcompose.R
import com.example.todolistcompose.presentation.utils.Screen
import com.example.todolistcompose.presentation.viewmodels.TodoScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun TodosScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    todoViewModel: TodoScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val todoList by todoViewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Todos") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddTodosScreen.route)
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    ) {
        if (todoList.isNotEmpty())
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
            ) {
                items(todoList) { todo ->
                    Text(todo.task)
                }
            }
        if (todoList.isEmpty())
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_empty_todo_list),
                    contentDescription = "Empty List Image",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
                Text(
                    text = "There are no todos to show here",
                    style = MaterialTheme.typography.h5,
                )
                Text(
                    text = "Try adding one today!",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
                )
            }
    }
}