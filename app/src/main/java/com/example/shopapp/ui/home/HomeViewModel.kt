package com.example.shopapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.ItemList
import com.example.shopapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val text = MutableLiveData<ItemList>()

    fun getList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    text.value = repo.getData()
                }
            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
            }
        }
    }

    fun getText() = text
}