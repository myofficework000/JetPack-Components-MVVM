package com.abhishek.mvvm_retrofit_coroutine.data.repository

import com.abhishek.mvvm_retrofit_coroutine.data.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("Category")
    suspend fun getCategories(): Response<CategoryResponse>

    companion object {
        fun getInstance(): ApiService = ApiClient.retrofit.create(ApiService::class.java)
    }
}