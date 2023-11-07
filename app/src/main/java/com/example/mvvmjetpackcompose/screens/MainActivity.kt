package com.example.mvvmjetpackcompose.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        setContent {
            MvvmJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }





    @Composable
    fun AppNavigation(){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination ="category"){
            composable(route = "category"){
                QuoteListScreen{
                    navController.navigate("details/${it}")
                }
            }
            composable(route = "details/{category}",
                arguments = listOf(
                    navArgument("category"){
                        type = NavType.StringType
                    }
                )
            ){
                QuoteDetailsScreen()
            }
        }
    }


}





