package com.example.shopapp.di.application

import android.app.Application
import com.example.shopapp.di.component.ApplicationComponent
import com.example.shopapp.di.component.DaggerApplicationComponent

class MyApplication : Application() {
    private val component by lazy { DaggerApplicationComponent.builder()
        .application(this)
        .build()}

    fun getApplicationComponent(): ApplicationComponent = component
}