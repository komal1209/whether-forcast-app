package com.denave.contentrepo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherforecastingapplication.R
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.data.model.WeatherDataList
import com.example.weatherforecastingapplication.utils.Utility
import kotlinx.android.synthetic.main.fragment_current.*
import kotlinx.android.synthetic.main.video_item_adapter.view.*


class VideoGridRecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = listOf<WeatherDetails>()
   
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DocViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.video_item_adapter, parent, false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as DocViewHolder
        movieViewHolder.bindView(dataList[position])
    }

    fun setDataList(contentDataList: List<WeatherDetails>) {
        this.dataList = contentDataList
        notifyDataSetChanged()
    }

    inner class DocViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(dataList: WeatherDetails) {
            itemView.cityText.text = dataList.name+" , "+dataList.country
            itemView.temp.text = "Temp : "+Utility.convertTempFahrenheitToCelsius((dataList.temp)!!.toDouble())
            itemView.press.text = "Pressure : "+dataList.pressure
            itemView.hum.text = "Humidity : "+dataList.humidity
            itemView.windSpeed.text = "Speed : "+dataList.speed
            itemView.windDeg.text = "Speed : xd"+dataList.deg

        }
    }
}