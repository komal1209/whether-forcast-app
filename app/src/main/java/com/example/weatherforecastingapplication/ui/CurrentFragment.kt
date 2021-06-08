package  com.example.weatherforecastingapplication.ui


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecastingapplication.R
import com.example.weatherforecastingapplication.data.WeatherDetails
import com.example.weatherforecastingapplication.utils.Utility
import kotlinx.android.synthetic.main.fragment_current.*


class CurrentFragment() : Fragment() {
    var count = 0;
    companion object {
        @kotlin.jvm.JvmField
        var TAG: String = CurrentFragment::class.java.getSimpleName();
    }

    private lateinit var viewModel: WeatherViewModel
    private var locationManager: LocationManager? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private val locationPermissionCode = 2
    var AppId = "2e65127e909e178d0af311a81f39948c"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        return inflater.inflate(R.layout.fragment_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationManager = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager?
        try {
            if ((ContextCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationPermissionCode
                )
            }
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } catch (ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available")
        }
        swipeContainer!!.setOnRefreshListener {
            count=0;
            apiCall()
        }
        lifecycleScope.launch {
            myTextView.text = "Starting"
            delay(1000L)
            myTextView.text = "Processing"
            delay(2000L)
            myTextView.text = "Done"
        }
       apiCall();
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity!!, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity!!, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun apiCall() {
        val count=0;
        if (Utility.isNetworkAvailable(activity)) {
            //  progressBar.visibility = View.VISIBLE
            var units="metric"
            var exclude="hourly,daily,minutely,alerts"
            try {
                viewModel.getWeatherDataFromServer("" + latitude, "" + longitude, AppId,units,exclude)
                    .observe(activity!!, Observer {
                        if (it != null) {

                            cityText.text = it.list.get(0).name +" , "+it.list.get(0).sys.country
                            temp.text = "Temp : "+it.list.get(0).main.temp
                            press.text = "Pressure : "+it.list.get(0).main.pressure
                            hum.text = "Humidity : "+it.list.get(0).main.humidity
                            windSpeed.text = " Wind Speed: "+it.list.get(0).wind.speed
                            windDeg.text = " Wind Degree: "+it.list.get(0).wind.deg


                            for (contentData in it.list) {
                                var weatherData = WeatherDetails();

                                weatherData.name = contentData.name
                                weatherData.lat = ""+contentData.coord.lat
                                weatherData.long = ""+contentData.coord.lon
                                weatherData.temp = ""+contentData.main.temp
                                weatherData.feels_like = ""+contentData.main.feelsLike
                                weatherData.temp_min = ""+contentData.main.tempMin
                                weatherData.temp_max = ""+contentData.main.tempMax
                                weatherData.pressure = ""+contentData.main.pressure
                                weatherData.humidity = ""+contentData.main.humidity
                                weatherData.dt = ""+contentData.dt
                                weatherData.speed = ""+contentData.wind.speed
                                weatherData.deg = ""+contentData.wind.deg
                                weatherData.country = contentData.sys.country
                                weatherData.main = contentData.weather.get(0).main
                                weatherData.description = contentData.weather.get(0).description
                                if(count<it.list.size){
                                    viewModel.insertWeatherDataInLocalDB(weatherData)
                                }
                            }
                            //  progressBar.visibility = View.GONE
                            swipeContainer.isRefreshing = false
                        }
                    })
            } catch (e: Exception) {
                e.printStackTrace();
            }
        } else {
            Utility.makeSnackBar(activity, resources.getString(R.string.network_unavailable))
            swipeContainer.isRefreshing = false
        }
    }



    //define the listener
    private val locationListener: LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            longitude = location.longitude
            latitude = location.latitude

            if (latitude > 0.0 && longitude > 0.0 && count == 0) {
                apiCall();
                count++;
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


}
