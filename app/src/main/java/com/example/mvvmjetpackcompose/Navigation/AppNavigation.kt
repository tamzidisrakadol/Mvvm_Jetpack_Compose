package com.example.mvvmjetpackcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmjetpackcompose.screens.QuoteDetailsScreen
import com.example.mvvmjetpackcompose.screens.QuoteListScreen

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