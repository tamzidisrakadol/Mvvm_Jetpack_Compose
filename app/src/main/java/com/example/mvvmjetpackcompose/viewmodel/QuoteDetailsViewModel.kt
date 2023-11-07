package com.example.mvvmjetpackcompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mvvmjetpackcompose.repository.QuoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteDetailsViewModel @Inject constructor(quoteRepo: QuoteRepo,savedStateHandle: SavedStateHandle):ViewModel() {

    val category = savedStateHandle.get<String>("category") ?: "Happy Quote"
    val details = quoteRepo.getQuotes(category)
}