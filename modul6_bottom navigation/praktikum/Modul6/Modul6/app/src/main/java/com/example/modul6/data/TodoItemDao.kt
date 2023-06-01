package com.example.modul6.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_items WHERE completed = 0")
    fun getAllTodoItems(): LiveData<List<TodoItem>>

    @Query("SELECT * FROM todo_items WHERE completed = 1")
    fun getCompletedTodoItems(): LiveData<List<TodoItem>>

    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Update
    suspend fun updateTodoItem(todoItem: TodoItem)
}

