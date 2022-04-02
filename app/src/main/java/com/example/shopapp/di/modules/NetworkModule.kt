package com.example.shopapp.di.modules

import com.example.shopapp.api.QuestApi
import com.example.shopapp.const.Constants.Companion.URL_BASE
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideService(retrofitObj: Retrofit): QuestApi =
        retrofitObj.create(QuestApi::class.java)
}