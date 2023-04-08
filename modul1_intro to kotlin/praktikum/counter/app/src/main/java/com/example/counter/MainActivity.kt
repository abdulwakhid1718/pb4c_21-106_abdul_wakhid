package com.example.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tambah: Button = findViewById(R.id.btn_tambah)
        val kurang: Button = findViewById(R.id.btn_kurang)
        val reset: Button = findViewById(R.id.reset)
        val txt: TextView = findViewById(R.id.label)
        var num = 0
        tambah.setOnClickListener{
            num += 1
            txt.text = num.toString()
        }
        kurang.setOnClickListener{
            num -= 1
            txt.text = num.toString()
        }
        reset.setOnClickListener{
            num = 0
            txt.text = num.toString()
        }
    }
}