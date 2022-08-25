package com.example.todolistcompose.di

import android.content.Context
import androidx.room.Room
import com.example.todolistcompose.data.todos.TodoDao
import com.example.todolistcompose.data.todos.TodoDatabase
import com.example.todolistcompose.data.todos.TodoRepoImpl
import com.example.todolistcompose.domain.repos.TodoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {
    @Provides
    @Singleton
    fun providesTodoDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "todo_database"
    ).build()

    @Provides
    @Singleton
    fun providesTodoDao(
        database: TodoDatabase
    ) = database.getTodoDao()

    @Provides
    @Singleton
    fun providesTodoRepo(
        todoDao: TodoDao
    ): TodoRepo = TodoRepoImpl(todoDao)
}