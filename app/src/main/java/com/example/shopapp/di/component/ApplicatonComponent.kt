package com.example.shopapp.di.component

import android.app.Application
import com.example.shopapp.di.modules.Binding
import com.example.shopapp.di.modules.GlideModule
import com.example.shopapp.di.modules.NetworkModule
import com.example.shopapp.di.modules.VMModule
import com.example.shopapp.ui.cart.CartFragment
import com.example.shopapp.ui.home.HomeFragment
import com.example.shopapp.ui.home.WebFragment
import com.example.shopapp.ui.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, VMModule::class, GlideModule::class, Binding::class])
@Singleton
interface ApplicationComponent {
    fun inject(activity: HomeFragment)
    fun inject(activity: ProfileFragment)
    fun inject(activity: CartFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}