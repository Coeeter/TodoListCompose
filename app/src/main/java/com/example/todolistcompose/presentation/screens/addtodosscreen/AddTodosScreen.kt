package com.example.todolistcompose.presentation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistcompose.R
import com.example.todolistcompose.presentation.screens.addtodosscreen.components.DatePickerTextField
import com.example.todolistcompose.presentation.viewmodels.AddTodosScreenViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AddTodosScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddTodosScreenViewModel = hiltViewModel()
) {
    val task by viewModel.task.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Todo") },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back to previous screen"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_add_todo),
                contentDescription = "Add Todo image",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = task,
                label = { Text(text = "Task") },
                onValueChange = {
                    viewModel.task.value = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            DatePickerTextField(
                modifier = Modifier.fillMaxWidth(),
                onDateChangeListener = { viewModel.timeToStart.value = it }
            ) {
                Text(text = "Pick starting date of todo")
            }
            Spacer(modifier = Modifier.height(10.dp))
            DatePickerTextField(
                modifier = Modifier.fillMaxWidth(),
                onDateChangeListener = { viewModel.timeToComplete.value = it }
            ) {
                Text(text = "Pick ending date of todo")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusManager.clearFocus()
                    viewModel.addTodo()
                        .onEach {
                            when (it) {
                                is AddTodosScreenViewModel.AddTodoState.Loading -> {
                                    isLoading = true
                                }
                                is AddTodosScreenViewModel.AddTodoState.Success -> {
                                    isLoading = false
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        "Successfully added Todo!",
                                        "Okay"
                                    )
                                    navController.popBackStack()
                                }
                                is AddTodosScreenViewModel.AddTodoState.Failure -> {
                                    isLoading = false
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                    scaffoldState.snackbarHostState.showSnackbar(it.message.toString())
                                }
                            }
                        }
                        .catch {
                            scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                            scaffoldState.snackbarHostState.showSnackbar(it.message.toString())
                            Log.d("poly", it.message.toString())
                        }
                        .launchIn(scope)
                }
            ) {
                Text(text = "Add Todo")
            }
        }
        AnimatedVisibility(
            visible = isLoading,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.secondary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}