package com.example.weatherforecastingapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforecastingapplication.data.WeatherDetails

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(weatherDetails: WeatherDetails?)

    @Query("SELECT  * FROM weather_details")
    fun getWeatherData(): LiveData<List<WeatherDetails>>?
}