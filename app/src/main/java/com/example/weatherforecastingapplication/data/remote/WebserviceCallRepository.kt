package com.example.weatherforecastingapplication.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.denave.contentrepo.data.remote.ApiClient.client
import com.example.weatherforecastingapplication.data.model.WeatherResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebServiceCallRepository {
    private val apiService: ApiHelper = client!!.create(ApiHelper::class.java)

    private object SingletonHelper {
        val INSTANCE = WebServiceCallRepository()
    }

    companion object {
        val instance: WebServiceCallRepository
            get() = SingletonHelper.INSTANCE
    }

    fun getWeatherDataFromServer(latitude:String,logitude:String,appKey:String,units:String,exclude:String): LiveData<WeatherResponse> {
        val data: MutableLiveData<WeatherResponse> = MutableLiveData<WeatherResponse>()
        apiService.getCurrentWeatherData(latitude,logitude,appKey,units,exclude)?.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                data.value = response.body()
            }
        })
        return data
    }
}