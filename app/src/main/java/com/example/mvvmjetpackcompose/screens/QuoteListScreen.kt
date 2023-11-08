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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmjetpackcompose.R
import com.example.mvvmjetpackcompose.utils.ApiResponse
import com.example.mvvmjetpackcompose.viewmodel.QuoteViewModel
import kotlinx.coroutines.delay


@Composable
fun QuoteListScreen(onClick : (category:String)-> Unit) {
    val quoteViewModel: QuoteViewModel = hiltViewModel()
    val categoryState = remember {
        quoteViewModel.categories
    }
    val categoryData = categoryState.collectAsState(initial = ApiResponse.Loading)
    when (val result = categoryData.value) {
        is ApiResponse.Success -> {
            val list = result.data
            Log.d("post","${list?.distinct()}")
            QuoteListShow(categoryNamesList = list!!.distinct(), onClick = onClick)
        }

        is ApiResponse.Failure -> {
            Log.d("post", result.msg)
        }

        ApiResponse.Loading -> {
            CategoryLoadingScreen()
            Log.d("post", "Loading....")
        }
    }


}

@Composable
fun CategoryLoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(1f),contentAlignment = Alignment.Center) {
        Text(
            text = "Loading...", style = TextStyle(
                fontSize = 30.sp, color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
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