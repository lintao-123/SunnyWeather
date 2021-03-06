package com.example.sunnyweather.logic.network

import com.example.sunnyweather.logic.network.ServiceCreator.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SunnyWeatherNetwork {
    //动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    //发起搜索城市数据请求
    suspend fun searchPlaces(query : String) = placeService.searchPlaces(query).await()
    private suspend fun  <T> Call<T>.await() :T{
        return suspendCoroutine {continuation ->
            enqueue(object : Callback<T>{

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }

    }
}