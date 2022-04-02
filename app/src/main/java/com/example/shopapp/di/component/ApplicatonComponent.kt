package com.example.shopapp.di.component

import android.app.Application
import com.example.shopapp.MainActivity
import com.example.shopapp.di.modules.Binding
import com.example.shopapp.di.modules.GlideModule
import com.example.shopapp.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class,  GlideModule::class, Binding::class])
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}