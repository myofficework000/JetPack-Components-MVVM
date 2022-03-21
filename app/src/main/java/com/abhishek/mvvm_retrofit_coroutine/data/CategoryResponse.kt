package com.abhishek.mvvm_retrofit_coroutine.data

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("categories")
    val list: List<Category>
)
