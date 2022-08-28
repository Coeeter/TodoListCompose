package com.example.todolistcompose.presentation.screens.tododetailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistcompose.presentation.screens.addtodosscreen.components.getDateString
import com.example.todolistcompose.presentation.viewmodels.TodoDetailsViewModel
import java.util.*

@Composable
fun TodoDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TodoDetailsViewModel = hiltViewModel()
) {
    val todo by viewModel.todo.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Details of Todo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Task:", style = MaterialTheme.typography.h5)
                if (todo != null)
                    Text(
                        todo!!.task,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
                    )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Time to start:", style = MaterialTheme.typography.h5)
                if (todo != null) {
                    val time = getTime(todo!!.timeToStartTask)
                    Text(
                        time,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Time to complete:", style = MaterialTheme.typography.h5)
                if (todo != null) {
                    val time = getTime(todo!!.timeToComplete)
                    Text(
                        time,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
                    )
                }
            }
        }
    }
}

private fun getTime(time: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return getDateString(year, month, day)
}