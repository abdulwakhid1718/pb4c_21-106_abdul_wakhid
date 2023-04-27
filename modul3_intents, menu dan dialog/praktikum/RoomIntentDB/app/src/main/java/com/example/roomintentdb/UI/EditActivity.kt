package com.example.roomintentdb.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.roomintentdb.R
import com.example.roomintentdb.Rooms.Constant
import com.example.roomintentdb.Rooms.Mhs
import com.example.roomintentdb.Rooms.MhsDB
import com.example.roomintentdb.databinding.ActivityEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    val db by lazy { MhsDB(this) }
    private var mhsNim: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListener()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                binding.buttonUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getMhs()
            }
            Constant.TYPE_UPDATE -> {
                binding.buttonSave.visibility = View.GONE
                binding.editNim.visibility = View.GONE
                getMhs()
            }
        }
    }

    fun setupListener(){
        binding.buttonSave.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.dataDao().addData(
                    Mhs(
                        binding.editNim.text.toString(),
                        binding.editNama.text.toString(),
                        binding.editProdi.text.toString(),
                        binding.editAlamat.text.toString()
                    )
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.dataDao().updateData(
                    Mhs(
                        mhsNim,
                        binding.editNama.text.toString(),
                        binding.editProdi.text.toString(),
                        binding.editAlamat.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun getMhs(){
        mhsNim = intent.getStringExtra("intent_id").toString()
        CoroutineScope(Dispatchers.IO).launch {
            val mhs = db.dataDao().getOneData( mhsNim )[0]
            binding.editNim.setText( mhs.nim )
            binding.editNama.setText( mhs.nama )
            binding.editProdi.setText( mhs.prodi )
            binding.editAlamat.setText( mhs.alamat )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}