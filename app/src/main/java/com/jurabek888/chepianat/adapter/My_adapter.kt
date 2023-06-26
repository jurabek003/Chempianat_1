package com.jurabek888.chepianat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jurabek888.chepianat.databinding.ItemRecakleBinding
import com.jurabek888.chepianat.madels.USer

class My_adapter(val list: MutableList<USer>, val rvAction: RvAction ):RecyclerView.Adapter<My_adapter.Vh>() {
    inner class Vh(val itemRecakleBinding: ItemRecakleBinding):ViewHolder(itemRecakleBinding.root){
        fun onBind(user:USer,position: Int){
            itemRecakleBinding.itemName.text=user.name
            itemRecakleBinding.itemNumber.text=user.number
            itemRecakleBinding.root.setOnLongClickListener {
                rvAction.deletUser(user, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRecakleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }
    interface RvAction{
        fun deletUser(user: USer,position: Int)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun sortList() {
        list.sortBy { it.name }
        notifyDataSetChanged()

    }
    @SuppressLint("NotifyDataSetChanged")
    fun sortID(){
        list.sortBy { it.id }
        notifyDataSetChanged()
    }

}