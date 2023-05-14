package com.example.tablayoutwa.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutwa.R
import com.example.tablayoutwa.databinding.ItemStatusBinding
import com.example.tablayoutwa.fragment.data.Status

class StatusAdapter : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    private val statusList: MutableList<Status> = mutableListOf()

    inner class StatusViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val binding: ItemStatusBinding = ItemStatusBinding.bind(view)
    }

    fun setData(data: List<Status>) {
        statusList.clear()
        statusList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        return StatusViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_status, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val statusItem = statusList[position]
        holder.binding.textStatusTitle.text = statusItem.nama
        holder.binding.imageStatus.setImageResource(statusItem.img)
        holder.binding.textCreatedAt.text = statusItem.tgl
    }

    override fun getItemCount(): Int {
        return statusList.size
    }

}