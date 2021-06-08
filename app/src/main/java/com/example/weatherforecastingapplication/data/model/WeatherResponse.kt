package com.example.weatherforecastingapplication.data.model

import com.google.gson.annotations.SerializedName

   
data class WeatherResponse (

   @SerializedName("message") var message : String,
   @SerializedName("cod") var cod : String,
   @SerializedName("count") var count : Int,
   @SerializedName("list") var list : ArrayList<List>

)