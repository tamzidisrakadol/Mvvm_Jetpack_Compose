package com.example.mvvmjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmjetpackcompose.repository.QuoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(private val quoteRepo: QuoteRepo) :ViewModel() {

    val quoteList = quoteRepo.getQuotes("Happy Quote")

    val categories = quoteRepo.getCategories()


}