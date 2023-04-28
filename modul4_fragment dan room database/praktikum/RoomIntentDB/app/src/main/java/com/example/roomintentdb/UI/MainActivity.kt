package com.example.roomintentdb.UI

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomintentdb.R
import com.example.roomintentdb.Rooms.Constant
import com.example.roomintentdb.Rooms.Mhs
import com.example.roomintentdb.Rooms.MhsDB
import com.example.roomintentdb.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val db by lazy { MhsDB(this) }
    lateinit var mhsAdapter: MhsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val dataMhs = db.dataDao().getData()
            Log.d("MainActivity", "response: $dataMhs")
            withContext(Dispatchers.Main){
                mhsAdapter.setData(dataMhs)
            }
        }
    }

    fun setupListener(){
        binding.buttonCreate.setOnClickListener {
//            startActivity(Intent(this, EditActivity::class.java))
            intentEdit("", Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(mhsNim: String, intentType: Int){
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", mhsNim)
                .putExtra("intent_type", intentType)
        )
    }

    fun setupRecyclerView(){
        mhsAdapter = MhsAdapter(arrayListOf(), object : MhsAdapter.OnAdapterListener{
            override fun onClick(mhs: Mhs) {
//                Toast.makeText(applicationContext, mhs.nim, Toast.LENGTH_SHORT).show()
                intentEdit(mhs.nim, Constant.TYPE_READ)
            }

            override fun onUpdate(mhs: Mhs) {
                intentEdit(mhs.nim, Constant.TYPE_UPDATE)
            }

            override fun onDelete(mhs: Mhs) {
                deleteDialog(mhs)
            }

        })

        binding.listNote.layoutManager = LinearLayoutManager(applicationContext)
        binding.listNote.adapter = mhsAdapter
    }

    private fun deleteDialog(mhs: Mhs){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Hapus")
            setMessage("Yakin Mau Hapus Data ${mhs.nama}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Yakin") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.dataDao().deleteData(mhs)
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}