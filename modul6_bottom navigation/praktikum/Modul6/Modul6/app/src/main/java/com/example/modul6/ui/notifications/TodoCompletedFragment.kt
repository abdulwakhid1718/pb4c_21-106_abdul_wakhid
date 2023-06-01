package com.example.modul6.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul6.data.TodoDatabase
import com.example.modul6.databinding.FragmentTodoCompletedBinding
import com.example.modul6.ui.TodoViewModel
import com.example.modul6.ui.TodoViewModelFactory

class TodoCompletedFragment : Fragment() {

    private var _binding: FragmentTodoCompletedBinding? = null
    private val binding get() = _binding!!

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoCompletedAdapter: TodoCompletedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoCompletedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        todoCompletedAdapter = TodoCompletedAdapter()
        binding.rvCompleted.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCompleted.adapter = todoCompletedAdapter

        val todoItemDao = TodoDatabase.getDatabase(requireContext()).todoItemDao()
        todoViewModel = ViewModelProvider(this, TodoViewModelFactory(todoItemDao)).get(TodoViewModel::class.java)

        todoViewModel.completedTodoItems.observe(viewLifecycleOwner, Observer { todoItems ->
            todoCompletedAdapter.submitList(todoItems)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}