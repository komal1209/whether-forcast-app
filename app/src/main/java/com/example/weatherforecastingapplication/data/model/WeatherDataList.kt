package com.example.weatherforecastingapplication.data.model

import com.google.gson.GsonBuilder

class WeatherDataList(var name: String,
                      var lat : String,
                      var long: String,
                      var temp: String,
                      var feels_like: String,
                      var temp_min: String,
                      var temp_max: String,
                      var pressure: String,
                      var humidity: String,
                      var dt: String,
                      var speed: String,
                      var country: String,
                      var main: String,
                      var description: String,
                      var deg: String) {
    override fun toString(): String {
        return GsonBuilder().create().toJson(this, WeatherDataList::class.java)
    }
}


