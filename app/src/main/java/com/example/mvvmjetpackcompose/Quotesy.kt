package com.example.mvvmjetpackcompose

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmjetpackcompose.viewmodel.QuoteViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Quotesy:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}