package com.example.mvvmjetpackcompose.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmjetpackcompose.R
import com.example.mvvmjetpackcompose.utils.ApiResponse
import com.example.mvvmjetpackcompose.viewmodel.QuoteViewModel


@Composable
fun QuoteListScreen(onClick: (category: String) -> Unit) {
    val quoteViewModel: QuoteViewModel = hiltViewModel()

    // State to hold the categories list
    var categoryList by remember { mutableStateOf<List<String>?>(null) }

    // Fetch the data only once when the composable is first launched
    LaunchedEffect(quoteViewModel.categories) {
        if (categoryList == null) {
            quoteViewModel.categories.collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        // Update the categoryList when the API call is successful
                        categoryList = response.data?.distinct()
                        Log.d("post", "Categories: ${categoryList}")
                    }
                    is ApiResponse.Failure -> {
                        Log.d("post", "Fail Msg: ${response.msg}")
                    }
                    ApiResponse.Loading -> {
                        Log.d("post", "Loading api calls")
                    }
                }
            }
        }
    }

    // Render the UI based on the categoryList
    categoryList?.let { categories ->
        QuoteListShow(categoryNamesList = categories, onClick = onClick)
    } ?: CategoryLoadingScreen()
}


@Composable
fun CategoryLoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(1f),contentAlignment = Alignment.Center) {
        Text(
            text = "Loading...", style = TextStyle(
                fontSize = 18.sp, color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center),

        )
    }

}


@Composable
fun QuoteListShow(categoryNamesList: List<String>,onClick : (category:String)-> Unit) {
    Surface(
        modifier = Modifier.background(Color.White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categoryNamesList) {
                QuoteListItem(category = it,onClick)
            }
        }
    }
}


@Composable
fun QuoteListItem(category: String,onClick : (category:String)-> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clickable {
                onClick(category)
            }
            .clip(RoundedCornerShape(5.dp))
            .border(2.dp, Color(0xFF6F6B6B)),

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = category, style = TextStyle(
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }

    }
}