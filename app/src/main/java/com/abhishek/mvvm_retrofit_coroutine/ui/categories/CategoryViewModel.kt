package com.abhishek.mvvm_retrofit_coroutine.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.mvvm_retrofit_coroutine.data.Category
import com.abhishek.mvvm_retrofit_coroutine.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: Repository): ViewModel() {

    val categoryList = MutableLiveData<List<Category>>()
    val error = MutableLiveData<String>()
    val processing = MutableLiveData<Boolean>()

    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                processing.postValue(true)
                val response = repository.getCategories()
                processing.postValue(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to load data. Error code: ${response.code()}")
                    return@launch
                }

                response.body()?.let {
                    if(it.status == 0) {
                        categoryList.postValue(it.list)
                    } else {
                        error.postValue(it.message)
                    }
                }
            } catch (e: Exception) {
                error.postValue("Error is : $e")
                e.printStackTrace()
                processing.postValue(false)
            }
        }
    }


    /*fun findPair(ar: Array<Int>, sum: Int) {
        val map = HashMap<Int, Int>()

        ar.forEach {
            if(map.containsKey(sum-it)) {
                println("${sum-it}, $it")
            } else {
                map.put(it, it)
            }
        }
    }*/




}