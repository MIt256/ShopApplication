package com.example.shopapp.api

import com.example.shopapp.const.Constants.Companion.CURRENCY_QUERY
import com.example.shopapp.const.Constants.Companion.URL_ENDPOINT_GET
import com.example.shopapp.const.Constants.Companion.URL_QUERY
import com.example.shopapp.model.currencyApi.Currency
import com.example.shopapp.model.findingApi.FindResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestApi {
    @GET(URL_ENDPOINT_GET)
    suspend fun getDataApi(@Query(URL_QUERY) query:String): Response<FindResponse>

    @GET(CURRENCY_QUERY)
    suspend fun getCurrencyApi(): Response<Currency>

}