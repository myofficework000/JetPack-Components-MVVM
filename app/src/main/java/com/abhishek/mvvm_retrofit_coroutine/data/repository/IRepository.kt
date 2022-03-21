package com.abhishek.mvvm_retrofit_coroutine.data.repository

import com.abhishek.mvvm_retrofit_coroutine.data.CategoryResponse
import retrofit2.Response

interface IRepository {
    suspend fun getCategories(): Response<CategoryResponse>
}