package com.example.roomintentdb.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomintentdb.R
import com.example.roomintentdb.Rooms.Mhs
import com.example.roomintentdb.databinding.AdapterMainBinding

class MhsAdapter( private val mahasiswa: ArrayList<Mhs>, private val listener: OnAdapterListener ) : RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MhsViewHolder {
        return MhsViewHolder(
//            AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
        )
    }

    override fun getItemCount() = mahasiswa.size

    override fun onBindViewHolder(holder: MhsViewHolder, position: Int) {
        val mhs = mahasiswa[position]
        holder.binding.textNim.text = mhs.nim
        holder.binding.textNama.text = mhs.nama
        holder.binding.textNama.setOnClickListener {
            listener.onClick(mhs)
        }
        holder.binding.iconEdit.setOnClickListener {
            listener.onUpdate(mhs)
        }
        holder.binding.iconDelete.setOnClickListener {
            listener.onDelete(mhs)
        }
    }

    inner class MhsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding: AdapterMainBinding = AdapterMainBinding.bind(view)
    }

    fun setData(list: List<Mhs>){
        mahasiswa.clear()
        mahasiswa.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(mhs: Mhs)
        fun onUpdate(mhs: Mhs)
        fun onDelete(mhs: Mhs)
    }
}