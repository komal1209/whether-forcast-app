package com.example.weatherforecastingapplication.data.remote


import com.example.weatherforecastingapplication.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiHelper {

    @GET("data/2.5/find?")
    fun getCurrentWeatherData(
        @Query("lat") lat: String?, @Query("lon") lon: String?, @Query(
            "APPID"
        ) app_id: String?,@Query("units") units: String?,@Query("exclude") exclude: String?
    ): Call<WeatherResponse>?
    fun getWeatherResponse(@Url url: String): Call<WeatherResponse>

}
