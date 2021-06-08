package com.example.weatherforecastingapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.denave.contentrepo.data.local.WeatherRepositoryImpl
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.data.model.WeatherResponse

import com.example.weatherforecastingapplication.data.remote.WebServiceCallRepository
import java.util.concurrent.Executors

class WeatherViewModel(application: Application) : AndroidViewModel(application){
    private var weatherRepository: WeatherRepositoryImpl? = null
    private var weatherResponse: LiveData<WeatherResponse> = MutableLiveData()
    private var weatherLiveData: LiveData<List<WeatherDetails>> = MutableLiveData<List<WeatherDetails>>()

    init {
        weatherRepository = WeatherRepositoryImpl(application)
    }

    fun getWeatherDataFromServer(latitude:String, logitude:String, appKey:String, units:String, exclude: String): LiveData<WeatherResponse> {
        weatherResponse = WebServiceCallRepository.instance.getWeatherDataFromServer(latitude, logitude, appKey, units, exclude)
        return weatherResponse
    }

    fun getWeatherDataFromDB(): LiveData<List<WeatherDetails>> {
        weatherLiveData = weatherRepository?.appDao?.getWeatherData()!!
        return weatherLiveData
    }

    fun insertWeatherDataInLocalDB(weatherData: WeatherDetails) {
        Executors.newSingleThreadExecutor().execute { weatherRepository?.insertWeatherData(weatherData) }
    }



}