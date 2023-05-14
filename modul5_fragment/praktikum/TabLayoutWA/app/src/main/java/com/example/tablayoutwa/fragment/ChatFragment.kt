package com.example.tablayoutwa.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayoutwa.databinding.FragmentChatBinding
import com.example.tablayoutwa.fragment.adapters.ChatAdapter
import com.example.tablayoutwa.fragment.data.Chat

class ChatFragment : Fragment() {

    private lateinit var adapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding

    private val items = listOf(
        Chat("Adi", "Halo, apa kabar?", 0, "Kemarin"),
        Chat("Budi", "Saya baik, terima kasih. Bagaimana denganmu?", 1, "Kemarin"),
        Chat("Ali", "Saya baik, terima kasih sudah bertanya.", 2, "Kemarin"),
        Chat("Bintang", "Bagus dong.", 2, "Kemarin"),
        Chat("Citra", "Hai, apa kabar kamu?", 1, "Kemarin"),
        Chat("Dewi", "Saya sedang sibuk dengan pekerjaan.", 0, "Kemarin"),
        Chat("Putri", "Semangat ya! Jangan lupa istirahat.", 2, "04/05/2023"),
        Chat("Azrel", "Terima kasih. Aku akan istirahat sebentar.", 0, "04/05/2023"),
        Chat("Eko", "Halo, ada rencana apa hari ini?", 1, "04/05/2023"),
        Chat("Fitri", "Saya belum menentukan rencana. Bagaimana denganmu?", 1, "04/05/2023"),
        Chat("Mawar", "Saya ingin pergi ke bioskop.", 0, "03/05/2023"),
        Chat("SOLIHIN", "Bagus! Ayo nonton bareng.", 2, "03/05/2023"),
        Chat("Riski", "Halo, sudah makan belum?", 1, "02/05/2023"),
        Chat("Lesti", "Belum, sedang dalam perjalanan pulang.", 1, "02/05/2023"),
        Chat("Raja Thiland", "Baiklah, kabari kalau sudah sampai.", 2, "02/05/2023"),
        Chat("Sambo", "Siap! Jendral.", 2, "01/05/2023")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = ChatAdapter()
        adapter.setData(items)

        binding.recyclerChatView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerChatView.adapter = adapter

        return view
    }
}