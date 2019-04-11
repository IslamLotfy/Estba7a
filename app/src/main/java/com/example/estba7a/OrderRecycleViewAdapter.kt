package com.example.estba7a

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estba7a.databinding.OrdersListItemBinding

class OrderRecycleViewAdapter(private var orders: List<Order?> = listOf()) : RecyclerView.Adapter<OrderRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrdersListItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding,parent.context)
    }

    override fun getItemCount()= orders.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(orders[position])

    fun updateOrders(ordersList: List<Order?>){
        orders = ordersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: OrdersListItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order?){
            binding.itemsRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.itemsRecyclerView.adapter = OrderItemsRecyclerView(order!!.items)
            binding.order = order
        }
    }
}