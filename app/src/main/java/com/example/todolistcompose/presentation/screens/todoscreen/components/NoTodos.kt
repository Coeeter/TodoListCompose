package com.example.todolistcompose.presentation.screens.todoscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.todolistcompose.R

@Composable
fun NoTodos() {
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