package com.example.shopapp.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.repository.Repository
import com.example.shopapp.ui.model.AppItem
import com.example.shopapp.ui.static.Profile
import javax.inject.Inject

class CartViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val cartItems = MutableLiveData<ArrayList<AppItem>>()

    init{
        cartItems.value = Profile.cartList
    }

    fun getItems() = cartItems

}