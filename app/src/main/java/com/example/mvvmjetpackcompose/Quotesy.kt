package com.example.mvvmjetpackcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Quotesy:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}