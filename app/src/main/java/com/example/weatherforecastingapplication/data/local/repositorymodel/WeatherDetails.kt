package com.example.weatherforecastingapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_details")
class WeatherDetails {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("lat")
    @Expose
    var lat: String? = null
    @SerializedName("long")
    @Expose
    var long: String? = null
    @SerializedName("temp")
    @Expose
    var temp: String? = null
    @SerializedName("feels_like")
    @Expose
    var feels_like: String? = null
    @SerializedName("temp_min")
    @Expose
    var temp_min: String? = null
    @SerializedName("temp_max")
    @Expose
    var temp_max: String? = null
    @SerializedName("pressure")
    @Expose
    var pressure: String? = null
    @SerializedName("humidity")
    @Expose
    var humidity: String? = null
    @SerializedName("dt")
    @Expose
    var dt: String? = null
    @SerializedName("speed")
    @Expose
    var speed: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("main")
    @Expose
    var main: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("deg")
    @Expose
    var deg: String? = null

}