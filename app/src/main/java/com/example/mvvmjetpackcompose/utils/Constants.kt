package com.example.mvvmjetpackcompose.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> apiResult(call:suspend ()->Response<T>):Flow<ApiResponse<T?>> = flow {
    emit(ApiResponse.Loading)
    try {
        val c = call()
        c.let {
            if (c.isSuccessful){
                emit(ApiResponse.Success(it.body()))
            }else{
                c.errorBody()?.let { errorMsg->
                    errorMsg.close()
                    emit(ApiResponse.Failure(errorMsg.toString()))
                }
            }
        }
    }catch (e:Exception){
        e.printStackTrace()
        emit(ApiResponse.Failure(e.message.toString()))
    }
}