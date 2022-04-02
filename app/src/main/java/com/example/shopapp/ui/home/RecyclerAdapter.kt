package com.example.shopapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.ListItemBinding
import com.example.shopapp.model.Item

class RecyclerAdapter(val clickListener: (Int) -> Unit):   RecyclerView.Adapter<RecyclerAdapter.ItemHolder>() {

    private var recyclerViewItems = ArrayList<Item>()

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(item: Item) = with(binding) {
            id.text = item.id.toString()
            category.text = item.category
            price.text = item.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener(position)
        }
        holder.bind(recyclerViewItems[position])
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addToList(items: ArrayList<Item>) {
        recyclerViewItems = items
        notifyDataSetChanged()
    }
}