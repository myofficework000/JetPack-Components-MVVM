package com.abhishek.mvvm_retrofit_coroutine.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.abhishek.mvvm_retrofit_coroutine.R
import com.abhishek.mvvm_retrofit_coroutine.data.Category
import com.abhishek.mvvm_retrofit_coroutine.databinding.ViewHolderCategoryBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(val binding: ViewHolderCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    public fun bind(category: Category) {
        binding.apply {
            tvCategory.text = category.categoryTitle
            val url = "https://psmobitech.com/myshop/images/${category.imageUrl}"

            Picasso.get().load(url).placeholder(R.drawable.ic_default).into(ivCategoryImage)
        }
    }
}