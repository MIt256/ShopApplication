package com.example.shopapp.model.findingApi

import com.example.shopapp.ui.model.AppItem

data class ShopItem(
    val galleryURL: List<String> ,
    val globalId: List<String>,
    val itemId: List<String>,
    val location: List<String>,
    val primaryCategory: List<PrimaryCategory>,
    val sellingStatus: List<SellingStatus>,
    val subtitle: List<String>,
    val title: List<String>,
    val viewItemURL: List<String>
) {
    fun mapToAppItem() =
        AppItem(
            galleryURL[0],
            globalId[0],
            itemId[0],
            location[0],
            primaryCategory[0].categoryName[0],
            sellingStatus[0].currentPrice[0].currencyId,
            sellingStatus[0].currentPrice[0].__value__,
            sellingStatus[0].sellingState[0],
            title[0],
            viewItemURL[0]
        )
}
