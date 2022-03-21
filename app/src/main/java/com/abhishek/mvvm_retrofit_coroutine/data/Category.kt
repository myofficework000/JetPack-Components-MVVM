package com.abhishek.mvvm_retrofit_coroutine.data

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id")
    val id: Long,

    @SerializedName("category_name")
    val categoryTitle: String,

    @SerializedName("category_image_url")
    val imageUrl: String
)