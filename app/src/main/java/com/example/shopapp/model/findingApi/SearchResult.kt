package com.example.shopapp.model.findingApi

import com.example.shopapp.ui.model.AppItem
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("@count")
    val count: String,
    @SerializedName("item")
    val shopItems: ArrayList<ShopItem>
) {
    fun mapToAppItem() =
        shopItems.map { it.mapToAppItem() }
}