package com.example.mvvmjetpackcompose.screens


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmjetpackcompose.model.QuoteModel
import com.example.mvvmjetpackcompose.utils.ApiResponse
import com.example.mvvmjetpackcompose.viewmodel.QuoteDetailsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun QuoteDetailsScreen(){
    val quoteViewModel:QuoteDetailsViewModel = hiltViewModel()
    val categoryState = remember {
        quoteViewModel.details
    }
    val isScreenLoaded = remember{
        mutableStateOf(false)
    }

    val categoryData = categoryState.collectAsState(initial = ApiResponse.Loading)
    when (val result = categoryData.value) {
        is ApiResponse.Success -> {
            val list = result.data
            Log.d("post","$list")

            LaunchedEffect(key1 = true){
                delay(2000)
                isScreenLoaded.value = true
            }

            if (isScreenLoaded.value){
                QuoteDetailsShow(quoteList = list!!)
            }else{
                QuoteDetailsLoadingScreen()
            }

        }
        is ApiResponse.Failure -> {
            Log.d("post", result.msg)
        }

        ApiResponse.Loading -> {
            QuoteDetailsLoadingScreen()

        }
    }


}



@Composable
fun QuoteDetailsShow(quoteList:List<QuoteModel>){
    Surface(
        modifier = Modifier.background(Color.White)
    ) {
        LazyColumn(content = {
            items(quoteList){
                DetailsItem(quote = it.text)
            }
        })
    }

}

@Composable
fun QuoteDetailsLoadingScreen() {

    Box(modifier = Modifier.fillMaxSize(1f),contentAlignment = Alignment.Center) {
        Text(
            text ="Loading...", style = TextStyle(
                fontSize = 18.sp, color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
            )
        )
    }

}


@Composable
fun DetailsItem(quote:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.Black),
    ){
        Text(text = quote, modifier = Modifier.padding(5.dp),style = TextStyle(
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ) )
    }
}