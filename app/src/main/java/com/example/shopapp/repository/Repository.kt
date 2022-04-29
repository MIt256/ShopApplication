package com.example.shopapp.repository

import com.example.shopapp.api.QuestApi
import com.example.shopapp.model.currencyApi.Currency
import com.example.shopapp.model.currencyApi.CurrencyItem
import com.example.shopapp.model.findingApi.ShopItem
import com.example.shopapp.ui.model.AppItem
import javax.inject.Inject

class Repository @Inject constructor(private val api: QuestApi) {

    suspend fun getData(query:String): ArrayList<AppItem> {
        val response = api.getDataApi(query).body()
        val result = response!!.findItemsByKeywordsResponse[0].searchResult[0]
            return result.mapToAppItem()
    }


}