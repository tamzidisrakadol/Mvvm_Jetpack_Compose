package com.example.mvvmjetpackcompose.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mvvmjetpackcompose.Navigation.AppNavigation
import com.example.mvvmjetpackcompose.ui.theme.MvvmJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmJetpackComposeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title =
                        {Text(text = "Quotes Compose")},
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color(0xFF82CAFF),
                                titleContentColor = Color.White
                            )
                        )
                    }

                ) {
                    Box(modifier = Modifier.padding(it)) {
                        AppNavigation()
                    }
                }
            }
        }
    }


}





