package com.example.recyclerview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.data.itemData

class MainActivity : AppCompatActivity() {

    private val items = listOf(
        itemData("Daffa", R.drawable.img1),
        itemData("Sananta", R.drawable.img2),
        itemData("Jordi Amat", R.drawable.img3),
        itemData("Risky Ridho", R.drawable.img4),
        itemData("Irianto", R.drawable.img5),
        itemData("Doni", R.drawable.img6),
        itemData("Edo Febriansyah", R.drawable.img7),
        itemData("Dimas Drajat", R.drawable.img8)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = Adapter(items)
    }
}
