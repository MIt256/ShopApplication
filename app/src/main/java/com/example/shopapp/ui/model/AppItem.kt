package com.example.shopapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppItem(
    val galleryURL: String="" ,
    val globalId: String="",
    val itemId: String="",
    val location: String="",
    val categoryName: String="",
    val currencyId: String="",
    val costValue: String="",
    val sellingState: String="",
    val title:String="",
    val viewItemURL: String=""
):Parcelable