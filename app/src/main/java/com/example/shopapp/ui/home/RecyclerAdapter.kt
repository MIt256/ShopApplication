package com.example.shopapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.shopapp.R
import com.example.shopapp.databinding.ListItemBinding

class RecyclerAdapter(var glide:RequestManager, val clickListener: (Int,String) -> Unit):   RecyclerView.Adapter<RecyclerAdapter.ItemHolder>() {
//TODO GLIDE FIX
    private var recyclerViewItems = ArrayList<com.example.shopapp.model.findingApi.Item>()

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(item: com.example.shopapp.model.findingApi.Item) = with(binding) {
            //TODO reformat
            glide.load(item.galleryURL[0])
                .into(binding.itemImage)
            title.text = item.title[0]
            category.text = item.primaryCategory[0].categoryName[0]
            price.text = item.sellingStatus[0].currentPrice[0].__value__
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener(position,recyclerViewItems[position].viewItemURL[0])
        }
        holder.bind(recyclerViewItems[position])
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addToList(items: ArrayList<com.example.shopapp.model.findingApi.Item>) {
        recyclerViewItems = items
        notifyDataSetChanged()
    }
}