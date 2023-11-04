package com.example.mvvmjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.mvvmjetpackcompose.ui.theme.MvvmJetpackComposeTheme
import com.example.mvvmjetpackcompose.utils.ApiResponse
import com.example.mvvmjetpackcompose.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCategoriesList()

        setContent {
            MvvmJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }

    private fun getCategoriesList(){
        lifecycleScope.launch {
            quoteViewModel.categories.collect{
                when(it){
                    is ApiResponse.Success ->{
                        val list = it.data
                        Log.d("post", list?.distinct().toString())
                    }
                    is ApiResponse.Failure -> {
                        Log.d("post", it.msg)

                    }
                    ApiResponse.Loading->{
                        Log.d("post","Loading....")

                    }
                }
            }
        }
    }

}

