package com.abhishek.mvvm_retrofit_coroutine.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhishek.mvvm_retrofit_coroutine.data.repository.Repository

class CategoryViewModelProvider(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}