package com.abhishek.mvvm_retrofit_coroutine.ui.categories

import com.abhishek.mvvm_retrofit_coroutine.Constants
import com.abhishek.mvvm_retrofit_coroutine.data.CategoryResponse
import com.abhishek.mvvm_retrofit_coroutine.data.repository.IRepository
import com.google.gson.Gson
import retrofit2.Response

class TestRepository: IRepository {
    override suspend fun getCategories(): Response<CategoryResponse> {
        val dummyResponse = Response.success(Gson().fromJson(Constants.CATEGORY_FAILURE_RESPONSE, CategoryResponse::class.java))
        return dummyResponse
    }
}