package com.example.modul6.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul6.data.TodoItemDao

class TodoViewModelFactory(private val todoItemDao: TodoItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(todoItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
