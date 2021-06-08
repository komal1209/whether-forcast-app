package com.example.weatherforecastingapplication.data.model

import com.google.gson.annotations.SerializedName

   
data class List (

   @SerializedName("id") var id : Int,
   @SerializedName("name") var name : String,
   @SerializedName("coord") var coord : Coord,
   @SerializedName("main") var main : Main,
   @SerializedName("dt") var dt : Int,
   @SerializedName("wind") var wind : Wind,
   @SerializedName("sys") var sys : Sys,
   @SerializedName("rain") var rain : String,
   @SerializedName("snow") var snow : String,
   @SerializedName("clouds") var clouds : Clouds,
   @SerializedName("weather") var weather : ArrayList<Weather>

)