package com.example.shopapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.di.VMKey
import com.example.shopapp.ui.VMFactory
import com.example.shopapp.ui.cart.CartViewModel
import com.example.shopapp.ui.home.HomeViewModel
import com.example.shopapp.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMModule {
    @Binds
    @IntoMap
    @VMKey(HomeViewModel::class)
    abstract fun bindVM(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(ProfileViewModel::class)
    abstract fun bindVMProfile(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @VMKey(CartViewModel::class)
    abstract fun bindVMCart(viewModel: CartViewModel): ViewModel

    @Binds
    abstract fun bindVMFactory(factory: VMFactory): ViewModelProvider.Factory
}