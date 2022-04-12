package com.example.shopapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.R
import com.example.shopapp.model.findingApi.FindResponse
import com.example.shopapp.model.findingApi.Item
import com.example.shopapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val liveDataResponse = MutableLiveData<ArrayList<Item>>()
    private val categories = MutableLiveData<MutableList<String>>()

    fun getList(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    val result = repo.getData(query)
                    liveDataResponse.value = result
                    if (!result.isNullOrEmpty())
                    setCategories()
                }
            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
            }
        }
    }

    fun getResult() = liveDataResponse
    fun getCategories() = categories

    private fun setCategories(){
        var tempCategories = arrayListOf("ALL")
        liveDataResponse.value?.forEach {
            if (!tempCategories.contains( it.primaryCategory[0].categoryName[0]))
                tempCategories.add(it.primaryCategory[0].categoryName[0])
        }
        categories.value = tempCategories
    }
}