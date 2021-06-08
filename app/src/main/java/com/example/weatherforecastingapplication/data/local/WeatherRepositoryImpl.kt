package com.denave.contentrepo.data.local

import android.app.Application
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.data.local.WeatherDao

class WeatherRepositoryImpl(application: Application?) {
    var appDao: WeatherDao?

    init {
        appDao = WeatherRoomDatabase.getDataBaseHolder(application!!)!!.DatabaseHolder()
    }

    fun insertWeatherData(contentRepo: WeatherDetails) {
        appDao!!.insertWeatherData(contentRepo)
    }

    fun getWeatherData() {

    }




}