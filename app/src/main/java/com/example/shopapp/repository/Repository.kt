package com.example.shopapp.repository

import com.example.shopapp.api.QuestApi
import com.example.shopapp.model.ItemList
import javax.inject.Inject

class Repository@Inject constructor(private val api: QuestApi) {

    suspend fun getData(): ItemList? {
        return api.getDataApi().body()
    }

}