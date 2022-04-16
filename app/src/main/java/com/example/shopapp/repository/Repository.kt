package com.example.shopapp.repository

import com.example.shopapp.api.QuestApi
import com.example.shopapp.model.findingApi.ShopItem
import javax.inject.Inject

class Repository @Inject constructor(private val api: QuestApi) {

    suspend fun getData(query:String): ArrayList<ShopItem> {
        val response = api.getDataApi(query).body()
            return response!!.findItemsByKeywordsResponse[0].searchResult[0].mapToAppItem()
    }

}