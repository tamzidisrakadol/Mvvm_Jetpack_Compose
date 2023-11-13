package com.example.mvvmjetpackcompose.screens


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.example.mvvmjetpackcompose.ui.theme.MvvmJetpackComposeTheme
import com.example.mvvmjetpackcompose.utils.ApiResponse
import com.example.mvvmjetpackcompose.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val quoteViewModel: QuoteViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
  //          getCategories()
            MvvmJetpackComposeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title =
                            { Text(text = "Quotes Compose") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color(0xFF82CAFF),
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                    //    AppNavigation()
                        QuoteListScreen()
                    }
                }
            }
        }
    }

    private fun getCategories() {
        lifecycleScope.launch {
            quoteViewModel.categories.collect{
                when(it){
                    is ApiResponse.Success->{
                        Log.d("post","${it.data?.distinct()}")
                    }
                    is ApiResponse.Failure->{
                        Log.d("post","${it.msg}")
                    }
                    ApiResponse.Loading->{
                        Log.d("post","Loading api calls")
                    }
                }
            }
        }

    }


}





