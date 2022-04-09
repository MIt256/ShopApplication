package com.example.shopapp.model.findingApi

import com.google.gson.annotations.SerializedName

data class ProductId(
    @SerializedName("@type")
    val type: String,
    val __value__: String
)