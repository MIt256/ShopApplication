package com.example.shopapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val text = MutableLiveData<com.example.shopapp.model.findingApi.FindResponse>()

    fun getList(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    text.value = repo.getData(query)
                }
            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
            }
        }
    }

    fun getText() = text
}