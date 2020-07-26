package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object {

        lateinit var context :Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}