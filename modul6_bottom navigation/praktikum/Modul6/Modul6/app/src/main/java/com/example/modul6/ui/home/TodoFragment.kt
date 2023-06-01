package com.example.modul6.ui.home

import com.example.modul6.data.TodoDatabase
import com.example.modul6.data.TodoItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul6.databinding.FragmentTodoBinding
import com.example.modul6.ui.TodoViewModel
import com.example.modul6.ui.TodoViewModelFactory

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoViewModel: TodoViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val todoAdapter = TodoAdapter()
        binding.todoView.layoutManager = LinearLayoutManager(requireContext())
        binding.todoView.adapter = todoAdapter

        val todoItemDao = TodoDatabase.getDatabase(requireContext()).todoItemDao()
        todoViewModel = ViewModelProvider(this, TodoViewModelFactory(todoItemDao)).get(TodoViewModel::class.java)

        todoViewModel.allTodoItems.observe(viewLifecycleOwner, Observer { todoItems ->
            todoAdapter.submitList(todoItems)
        })

        binding.addButton.setOnClickListener {
            val task = binding.editText.text.toString()
            if (task.isNotEmpty()) {
                val todoItem = TodoItem(task = task)
                todoViewModel.insertTodoItem(todoItem)
                binding.editText.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}