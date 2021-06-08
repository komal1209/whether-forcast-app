package com.denave.contentrepo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.data.local.WeatherDao

@Database(entities = [WeatherDetails::class], version = 1, exportSchema = false)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun DatabaseHolder(): WeatherDao?

    companion object {
        private var dataBaseHolder: WeatherRoomDatabase? = null
        fun getDataBaseHolder(context: Context): WeatherRoomDatabase? {
            try {
                if (dataBaseHolder == null) {
                    synchronized(WeatherRoomDatabase::class.java) {
                        dataBaseHolder = Room.databaseBuilder(
                            context.applicationContext,
                            WeatherRoomDatabase::class.java, "weather-db"
                        )
                            .build()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return dataBaseHolder
        }
    }
}