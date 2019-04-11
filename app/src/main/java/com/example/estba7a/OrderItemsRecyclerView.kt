package com.example.estba7a

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.estba7a.databinding.OrdersItemsListItemBinding

class OrderItemsRecyclerView(private var itemsList: List<Item> = listOf()) : RecyclerView.Adapter<OrderItemsRecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrdersItemsListItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(itemsList[position])

    fun updateItems(items: List<Item>) {
        itemsList = items
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : OrdersItemsListItemBinding,val context: Context) :RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item) {
            binding.item = item
        }
    }
}