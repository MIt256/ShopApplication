package com.example.shopapp.di.application

import android.app.Application
import android.content.Context
import com.example.shopapp.di.component.ApplicationComponent
import com.example.shopapp.di.component.DaggerApplicationComponent

class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

}
val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }