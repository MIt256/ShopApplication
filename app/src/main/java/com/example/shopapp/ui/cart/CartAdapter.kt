package com.example.shopapp.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.shopapp.R
import com.example.shopapp.databinding.ListItemBinding
import com.example.shopapp.ui.model.AppItem
import com.example.shopapp.ui.static.CurrencyInfo
import com.example.shopapp.ui.static.Profile

class CartAdapter(var glide:RequestManager, val clickListener: (Int,String) -> Unit):   RecyclerView.Adapter<CartAdapter.ItemHolder>() {
    //TODO GLIDE FIX
    var recyclerViewItems = ArrayList<AppItem>()
    var num =1.0
    var dollarcost = 2.6534

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(appItem: AppItem) = with(binding) {
            //TODO reformat
            itemImage.setImageResource(R.drawable.ic_image_not_supported)
            glide.load(appItem.galleryURL)
                .into(binding.itemImage)
            title.text = appItem.title
            category.text = appItem.categoryName
            var priceval = appItem.costValue.toDouble()
            priceval *= dollarcost
            priceval /= num
            price.text = String.format("%.2f", priceval)
            currency.text = Profile.currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener(position,recyclerViewItems[position].viewItemURL)
        }
        holder.bind(recyclerViewItems[position])
    }

    override fun getItemCount(): Int {
        return recyclerViewItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addToList(shopItems: ArrayList<AppItem>) {
        recyclerViewItems = shopItems
        CurrencyInfo.currency.forEach{ if (it.Cur_Abbreviation == "USD") dollarcost = it.Cur_OfficialRate }
        CurrencyInfo.currency.forEach{ if (it.Cur_Abbreviation == Profile.currency) num = it.Cur_OfficialRate }
        notifyDataSetChanged()
    }
}