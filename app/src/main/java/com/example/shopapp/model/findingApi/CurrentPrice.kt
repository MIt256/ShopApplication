package com.example.shopapp.model.findingApi

import com.google.gson.annotations.SerializedName

data class CurrentPrice(
    @SerializedName("@currencyId")
    val currencyId: String,
    val __value__: String
)