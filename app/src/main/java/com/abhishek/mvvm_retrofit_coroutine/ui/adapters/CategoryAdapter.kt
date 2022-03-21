package com.abhishek.mvvm_retrofit_coroutine.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.mvvm_retrofit_coroutine.data.Category
import com.abhishek.mvvm_retrofit_coroutine.databinding.ViewHolderCategoryBinding
import com.abhishek.mvvm_retrofit_coroutine.ui.viewholders.CategoryViewHolder

class CategoryAdapter(val categories: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size
}