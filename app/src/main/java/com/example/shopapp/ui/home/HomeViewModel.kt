package com.example.shopapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.model.findingApi.ShopItem
import com.example.shopapp.repository.Repository
import com.example.shopapp.ui.model.AppItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    private val allItems = MutableLiveData<ArrayList<AppItem>>()
    private val categories = MutableLiveData<MutableList<String>>()
    private val selectedItems = MutableLiveData<ArrayList<AppItem>>()

    fun getAllItems() = allItems
    fun getCategories() = categories
    fun getSelectedItems() = selectedItems

    fun setSelectedItems(position:Int){
        if (!allItems.value.isNullOrEmpty()) {
            //todo fix null case
            if (categories.value!![position] != "ALL") {
                CoroutineScope(Dispatchers.Default).launch {
                    val categoryItems = ArrayList<AppItem>()
                    val allItems = allItems.value
                    if (allItems != null) {
                        allItems.forEach {
                            if (it.categoryName == categories.value!![position]) categoryItems.add(
                                it
                            )
                        }
                    }
                    withContext(Dispatchers.Main) { selectedItems.value = categoryItems }
                }
            } else selectedItems.value = allItems.value
        } else selectedItems.value = arrayListOf()
    }

    fun getList(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    val result = repo.getData(query)
                    allItems.value = result
                    //TODO fix null case (clear recycler, categories)
                }
            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
            }
        }
    }

    fun setCategories(){
        if (!allItems.value.isNullOrEmpty()){
            var tempCategories = arrayListOf("ALL")
            allItems.value?.forEach {
                if (!tempCategories.contains( it.categoryName))
                    tempCategories.add(it.categoryName)
            }
        categories.value = tempCategories
        } else categories.value = arrayListOf()
    }
}