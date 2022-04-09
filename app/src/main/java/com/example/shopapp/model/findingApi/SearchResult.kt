package com.example.shopapp.model.findingApi

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("@count")
    val count: String,
    @SerializedName("item")
    val items: ArrayList<Item>
)