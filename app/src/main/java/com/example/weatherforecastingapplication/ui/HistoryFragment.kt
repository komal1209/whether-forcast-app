package com.example.weatherforecastingapplication.ui


import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.denave.contentrepo.adapter.VideoGridRecyclerAdapter

import com.example.weatherforecastingapplication.R
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.data.model.WeatherDataList
import com.example.weatherforecastingapplication.data.remote.ApiEndPoint
import com.example.weatherforecastingapplication.utils.ConstantUtil
import com.example.weatherforecastingapplication.utils.GridItemDecoration
import com.example.weatherforecastingapplication.utils.Utility
import kotlinx.android.synthetic.main.fragment_current.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.swipeContainer

class HistoryFragment() : Fragment() {

    companion object {
        @kotlin.jvm.JvmField
        var TAG: String = HistoryFragment::class.java.getSimpleName();
    }

    var gridSpanCount: Int = 2
    var gridSpacingPixel: Int = 10
    var gridSize: Int = 2
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndAdapter()
        getContentDataFromServer();

        swipeContainer!!.setOnRefreshListener {
            getContentDataFromServer();
        }
    }

    private fun initViewAndAdapter() {
        recyclerView.layoutManager = GridLayoutManager(activity, gridSpanCount)
        recyclerView.addItemDecoration(GridItemDecoration(gridSpacingPixel, gridSize))
    }

    private fun getContentDataFromServer() {
        var count: Boolean = true;

        try {
            viewModel.getWeatherDataFromDB().observe(activity!!, Observer {
                if (count) {
                    if (it != null ) {
                        val recycleViewAdapter = VideoGridRecyclerAdapter()
                        recyclerView.adapter = recycleViewAdapter
                        recycleViewAdapter.setDataList(it);
                    }
                    count = false
                    swipeContainer.isRefreshing = false
                }
            })
        } catch (e: Exception) {
            swipeContainer.isRefreshing = false
        }
    }

}
