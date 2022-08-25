package com.example.todolistcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistcompose.presentation.theme.TodoListComposeTheme
import com.example.todolistcompose.presentation.screens.AddTodosScreen
import com.example.todolistcompose.presentation.screens.TodoDetailsScreen
import com.example.todolistcompose.presentation.screens.TodosScreen
import com.example.todolistcompose.presentation.screens.UpdateTodosScreen
import com.example.todolistcompose.presentation.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodosScreen.route
                    ) {
                        composable(route = Screen.TodosScreen.route) {
                            TodosScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable(route = Screen.AddTodosScreen.route) {
                            AddTodosScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable(route = Screen.UpdateTodosScreen.route) {
                            UpdateTodosScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                        composable(
                            route = "${Screen.TodoDetailsScreen.route}/{id}",
                            arguments = listOf(
                                navArgument(name = "id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            TodoDetailsScreen(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}