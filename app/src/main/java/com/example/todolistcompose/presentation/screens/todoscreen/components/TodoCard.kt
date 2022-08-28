package com.example.todolistcompose.presentation.screens.todoscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolistcompose.data.todos.TodoModel
import com.example.todolistcompose.presentation.utils.Screen

@Composable
fun TodoCard(
    navController: NavController,
    todo: TodoModel,
    toggleCompleted: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(todo.isCompleted) }
    Card(elevation = 5.dp, modifier = modifier.clickable {
        navController.navigate("${Screen.TodoDetailsScreen.route}/${todo.id}")
    }) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isChecked, onCheckedChange = {
                isChecked = !isChecked
                toggleCompleted()
            })
            val textDecoration =
                if (isChecked) TextDecoration.LineThrough
                else TextDecoration.None
            Text(todo.task, style = TextStyle(textDecoration = textDecoration))
        }
    }
}