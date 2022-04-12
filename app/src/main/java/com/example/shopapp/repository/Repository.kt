package com.example.shopapp.repository

import com.example.shopapp.api.QuestApi
import com.example.shopapp.model.findingApi.FindResponse
import com.example.shopapp.model.findingApi.Item
import javax.inject.Inject

class Repository @Inject constructor(private val api: QuestApi) {

    suspend fun getData(query:String): ArrayList<Item> {
        val response = api.getDataApi(query).body()
            return response!!.findItemsByKeywordsResponse[0].searchResult[0].items
    }

}