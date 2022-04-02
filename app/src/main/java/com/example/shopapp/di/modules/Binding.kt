package com.example.shopapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface Binding {
    @Binds
    fun context(appInstance: Application): Context
}