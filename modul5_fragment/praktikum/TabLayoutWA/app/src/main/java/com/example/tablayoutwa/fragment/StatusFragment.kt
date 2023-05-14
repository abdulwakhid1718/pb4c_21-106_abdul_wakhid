package com.example.tablayoutwa.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayoutwa.R
import com.example.tablayoutwa.databinding.FragmentStatusBinding
import com.example.tablayoutwa.fragment.adapters.StatusAdapter
import com.example.tablayoutwa.fragment.data.Status

class StatusFragment : Fragment() {

    private lateinit var adapter: StatusAdapter
    private lateinit var binding: FragmentStatusBinding

    private val statuses = listOf(
        Status("Putri", R.drawable.status1, "17 menit yang lalu"),
        Status("Dewi", R.drawable.status1, "20 menit yang lalu"),
        Status("Eka", R.drawable.status1, "21 menit yang lalu"),
        Status("Azrel", R.drawable.status1, "23 menit yang lalu"),
        Status("Bintang", R.drawable.status1, "45 menit yang lalu"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatusBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = StatusAdapter()
        adapter.setData(statuses)

        binding.recyclerStatus.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerStatus.adapter = adapter

        return view
    }
}