package com.example.shopapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.R
import com.example.shopapp.model.findingApi.FindResponse
import com.example.shopapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val liveDataResponse = MutableLiveData<FindResponse>()
    private val categories = MutableLiveData<MutableList<String>>()

    fun getList(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    liveDataResponse.value = repo.getData(query)
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
        liveDataResponse.value?.findItemsByKeywordsResponse?.get(0)?.searchResult?.get(0)?.items?.forEach {
            if (!tempCategories.contains( it.primaryCategory[0].categoryName[0]))
                tempCategories.add(it.primaryCategory[0].categoryName[0])
        }
        categories.value = tempCategories
    }
}