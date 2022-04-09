package com.example.shopapp.api

import com.example.shopapp.const.Constants.Companion.URL_ENDPOINT_GET
import com.example.shopapp.model.findingApi.FindResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuestApi {
    @GET(URL_ENDPOINT_GET)
    suspend fun getDataApi(): Response<FindResponse>
}