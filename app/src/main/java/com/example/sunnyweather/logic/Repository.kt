package com.example.sunnyweather.logic


import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.PlaceResponse.Place
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {

        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}