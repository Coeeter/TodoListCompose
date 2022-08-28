package com.example.todolistcompose.data.todos

import com.example.todolistcompose.domain.repos.TodoRepo

class TodoRepoImpl(
    private val todoDao: TodoDao
) : TodoRepo {
    override fun getTodoList() = todoDao.getTodoList()
    override fun getTodoById(id: Int): TodoModel? = todoDao.getTodoById(id)
    override fun addTodo(todoModel: TodoModel) = todoDao.addTodo(todoModel)
    override fun deleteTodo(todoModel: TodoModel) = todoDao.deleteTodo(todoModel)
    override fun deleteAllCompletedTodos() = todoDao.deleteCompletedTodos()
}