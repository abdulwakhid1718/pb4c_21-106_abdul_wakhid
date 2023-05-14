package com.example.tablayoutwa.fragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutwa.R
import com.example.tablayoutwa.databinding.ItemChatBinding
import com.example.tablayoutwa.fragment.data.Chat

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val chatList: MutableList<Chat> = mutableListOf()

    fun setData(data: List<Chat>) {
        chatList.clear()
        chatList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = chatList[position]
        holder.binding.txtNama.text = chatItem.nama
        holder.binding.txtChat.text = chatItem.chat
        holder.binding.tglChat.text = chatItem.tgl
        if (chatItem.status == 0){
            holder.binding.checkList.visibility = View.GONE
        }else if(chatItem.status == 1){
            holder.binding.checkList.setImageResource(R.drawable.ic_check)
        }else if(chatItem.status == 2){
            holder.binding.checkList.setImageResource(R.drawable.ic_read)
        }

    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ChatViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val binding: ItemChatBinding = ItemChatBinding.bind(view)


    }
}
