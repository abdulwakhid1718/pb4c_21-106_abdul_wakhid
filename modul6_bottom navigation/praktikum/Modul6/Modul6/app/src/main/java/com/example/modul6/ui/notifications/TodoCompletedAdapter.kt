package com.example.modul6.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.modul6.data.TodoDatabase
import com.example.modul6.data.TodoItem
import com.example.modul6.databinding.ItemTodoCompletedBinding
import com.example.modul6.ui.TodoViewModel
import com.example.modul6.ui.TodoViewModelFactory


class TodoCompletedAdapter : ListAdapter<TodoItem, TodoCompletedAdapter.TodoCompletedViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCompletedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoCompletedBinding.inflate(inflater, parent, false)
        return TodoCompletedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoCompletedViewHolder, position: Int) {
        val todoItem = getItem(position)
        holder.bind(todoItem)
    }

    inner class TodoCompletedViewHolder(private val binding: ItemTodoCompletedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoItem: TodoItem) {
            binding.textView.text = todoItem.task
            binding.icHapus.setOnClickListener {
                deleteTodoItem(todoItem)
            }
        }

        private fun deleteTodoItem(todoItem: TodoItem) {
            (binding.root.context as? FragmentActivity)?.let {
                val todoItemDao = TodoDatabase.getDatabase(it).todoItemDao()
                val todoViewModel = ViewModelProvider(it, TodoViewModelFactory(todoItemDao))
                    .get(TodoViewModel::class.java)
                todoViewModel.deleteTodoItem(todoItem)
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