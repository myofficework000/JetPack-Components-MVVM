package com.abhishek.mvvm_retrofit_coroutine.ui.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.mvvm_retrofit_coroutine.data.repository.ApiService
import com.abhishek.mvvm_retrofit_coroutine.data.repository.Repository
import com.abhishek.mvvm_retrofit_coroutine.databinding.ActivityMainBinding
import com.abhishek.mvvm_retrofit_coroutine.ui.adapters.CategoryAdapter

class CategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCategories.layoutManager = LinearLayoutManager(baseContext)

        setupViewModel()
        setupObservers()
        viewModel.loadCategories()
    }

    private fun setupObservers() {

        viewModel.categoryList.observe(this) {
            adapter = CategoryAdapter(it)
            binding.rvCategories.adapter = adapter
        }

        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

        viewModel.processing.observe(this) {
            if(it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun setupViewModel() {
        val repository = Repository(ApiService.getInstance())
        val factory = CategoryViewModelProvider(repository)
        viewModel = ViewModelProvider(this, factory)[CategoryViewModel::class.java]
    }


}