package com.example.todolistcompose.presentation.screens.tododetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistcompose.R
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
    val data = listOf(
        hashMapOf(
            "header" to "Task:",
            "value" to (todo?.task ?: "Loading")
        ),
        hashMapOf(
            "header" to "Is completed:",
            "value" to if (todo?.isCompleted == true) "Yes" else "No"
        ),
        hashMapOf(
            "header" to "Time to start:",
            "value" to (todo?.timeToStartTask?.run { getTime(this) } ?: "Loading")
        ),
        hashMapOf(
            "header" to "Time to complete:",
            "value" to (todo?.timeToComplete?.run { getTime(this) } ?: "Loading")
        ),
    )

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
            Image(
                painterResource(id = R.drawable.ic_details),
                "",
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Column(modifier = modifier.padding(horizontal = 30.dp)) {
                for (item in data) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = item["header"]!!,
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = item["value"]!!,
                            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
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