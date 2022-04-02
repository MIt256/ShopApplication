package com.example.shopapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.repository.Repository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    //val text: LiveData<String> = _text

    fun getText() = _text
}