package com.abhishek.mvvm_retrofit_coroutine.ui.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.abhishek.mvvm_retrofit_coroutine.Constants
import com.abhishek.mvvm_retrofit_coroutine.data.Category
import com.abhishek.mvvm_retrofit_coroutine.data.CategoryResponse
import com.abhishek.mvvm_retrofit_coroutine.data.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {
    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var categoryListObserver: Observer<List<Category>>

    @Mock
    lateinit var processingObserver: Observer<Boolean>

    @Mock
    lateinit var errorObserver: Observer<String>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `getCategory success testing`() {
        runBlockingTest {

            val dummyResponse = Response.success(Gson().fromJson(Constants.CATEGORY_SUCCESS_RESPONSE, CategoryResponse::class.java))

            doReturn(dummyResponse).`when`(repository).getCategories()

            val viewModel = CategoryViewModel(repository)

            viewModel.categoryList.observeForever(categoryListObserver)
            viewModel.processing.observeForever(processingObserver)

            viewModel.loadCategories()

            verify(processingObserver).onChanged(true)
            verify(repository).getCategories()
            verify(processingObserver).onChanged(false)

            val expectedResult = Gson().fromJson(Constants.CATEGORY_SUCCESS_RESPONSE, CategoryResponse::class.java)

            verify(categoryListObserver).onChanged(expectedResult.list)

            viewModel.categoryList.removeObserver(categoryListObserver)
            viewModel.processing.removeObserver(processingObserver)
        }
    }

    @Test
    fun `getCategory returns no data for categories testing`() {
        runBlockingTest {

            val dummyResponse = Response.success(Gson().fromJson(Constants.CATEGORY_FAILURE_RESPONSE, CategoryResponse::class.java))

            doReturn(dummyResponse).`when`(repository).getCategories()

            val viewModel = CategoryViewModel(repository)

            viewModel.error.observeForever(errorObserver)
            viewModel.processing.observeForever(processingObserver)

            viewModel.loadCategories()

            verify(processingObserver).onChanged(true)
            verify(repository).getCategories()
            verify(processingObserver).onChanged(false)

            verify(errorObserver).onChanged("Failed to load data")

            viewModel.error.removeObserver(errorObserver)
            viewModel.processing.removeObserver(processingObserver)
        }
    }


    @Test
    fun `getCategory failure testing`() {
        runBlockingTest {
            val dummyResponse = Response.error<String>(500, ResponseBody.create("text/plain".toMediaType(), "Internal server error."))

            doReturn(dummyResponse).`when`(repository).getCategories()
            val viewModel = CategoryViewModel(repository)

            viewModel.error.observeForever(errorObserver)
            viewModel.processing.observeForever(processingObserver)

            viewModel.loadCategories()

            verify(processingObserver).onChanged(true)
            verify(repository).getCategories()
            verify(processingObserver).onChanged(false)

            val expectedResult = "Failed to load data. Error code: 500"
            verify(errorObserver).onChanged(expectedResult)

            viewModel.error.removeObserver(errorObserver)
            viewModel.processing.removeObserver(processingObserver)

        }
    }

    @Test(expected = RuntimeException::class)
    fun `loadCategories exception testing`() = runBlockingTest {

        val exception = RuntimeException("Internet is not available.")

        doThrow(exception).`when`(repository.getCategories())

        val viewModel = CategoryViewModel(repository)

        viewModel.error.observeForever(errorObserver)
        viewModel.processing.observeForever(processingObserver)

        viewModel.loadCategories()

        verify(processingObserver).onChanged(true)
        verify(repository).getCategories()
        verify(processingObserver).onChanged(false)

        val expectedResult = "Error is : $exception"
        verify(errorObserver).onChanged(expectedResult)

        viewModel.error.removeObserver(errorObserver)
        viewModel.processing.removeObserver(processingObserver)
    }
}