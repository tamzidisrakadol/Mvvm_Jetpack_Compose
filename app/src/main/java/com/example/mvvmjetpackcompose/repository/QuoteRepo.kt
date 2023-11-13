package com.example.mvvmjetpackcompose.repository


import com.example.mvvmjetpackcompose.api.ApiInterface
import com.example.mvvmjetpackcompose.utils.apiResult
import javax.inject.Inject

class QuoteRepo @Inject constructor(private val apiInterface: ApiInterface) {
    fun getQuotes(category: String) = apiResult {
        apiInterface.getQuotes("tweets[?(@.category==\"$category\")]")
    }

    fun getCategories() = apiResult {
        apiInterface.getCategories()
    }

}