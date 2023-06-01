package com.example.modul6.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.modul6.data.TodoDatabase
import com.example.modul6.data.TodoItem
import com.example.modul6.databinding.ItemTodoBinding
import com.example.modul6.ui.TodoViewModel
import com.example.modul6.ui.TodoViewModelFactory

class TodoAdapter : ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoItem = getItem(position)
        holder.bind(todoItem)
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoItem: TodoItem) {
            binding.textTask.text = todoItem.task
            binding.checkbox.isChecked = todoItem.completed

            binding.checkbox.setOnCheckedChangeListener { _, _ ->
                todoItem.completed = true
                updateTodoItem(todoItem)
            }
        }

        private fun updateTodoItem(todoItem: TodoItem) {
            val updatedItem = todoItem.copy(completed = todoItem.completed)
            (binding.root.context as? FragmentActivity)?.let {
                val todoItemDao = TodoDatabase.getDatabase(it).todoItemDao()
                val todoViewModel = ViewModelProvider(it, TodoViewModelFactory(todoItemDao)).get(
                    TodoViewModel::class.java)
                todoViewModel.updateTodoItem(updatedItem)
            }

        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
            return oldItem == newItem
        }
    }
}
