package com.example.mvvmjetpackcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmjetpackcompose.repository.QuoteRepo
import com.example.mvvmjetpackcompose.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

@HiltViewModel
class QuoteViewModel @Inject constructor(quoteRepo: QuoteRepo) :ViewModel() {
    val categories = quoteRepo.getCategories()
}