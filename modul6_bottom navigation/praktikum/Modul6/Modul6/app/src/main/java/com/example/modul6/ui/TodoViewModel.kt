package com.example.modul6.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul6.data.TodoItem
import com.example.modul6.data.TodoItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val todoItemDao: TodoItemDao) : ViewModel() {
    val allTodoItems: LiveData<List<TodoItem>> = todoItemDao.getAllTodoItems()
    val completedTodoItems: LiveData<List<TodoItem>> = todoItemDao.getCompletedTodoItems()

    fun insertTodoItem(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemDao.insertTodoItem(todoItem)
        }
    }

    fun updateTodoItem(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemDao.updateTodoItem(todoItem)
        }
    }

    fun deleteTodoItem(todoItem: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoItemDao.deleteTodoItem(todoItem)
        }
    }
}
