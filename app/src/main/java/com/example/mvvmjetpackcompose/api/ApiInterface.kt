package com.example.mvvmjetpackcompose.api

import com.example.mvvmjetpackcompose.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiInterface {

    companion object{
        const val base_Url = "https://api.jsonbin.io"
    }

    @GET("/v3/b/6542ef3d54105e766fca32cf?meta=false")
    suspend fun getQuotes(@Header("X-JSON-Path")category:String):Response<List<QuoteModel>>


    @GET("/v3/b/6542ef3d54105e766fca32cf?meta=false")
    @Headers("X-JSON-Path:tweets..category")
    suspend fun getCategories():Response<List<String>>

}