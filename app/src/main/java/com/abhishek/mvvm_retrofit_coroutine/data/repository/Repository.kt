package com.abhishek.mvvm_retrofit_coroutine.data.repository

class Repository(private val apiService: ApiService) {

    suspend fun getCategories() = apiService.getCategories()

}