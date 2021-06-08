package  com.example.weatherforecastingapplication.utils

import android.R
import android.R.attr.x
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.text.*
import java.util.*


class Utility {
    companion object {

        fun isNetworkAvailable(context: Context?): Boolean {
            if (context == null) return false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false
        }

        fun convertTempFahrenheitToCelsius(kelvin:Double):String{
            val celsius =kelvin-273;
            val formatter: NumberFormat = DecimalFormat("#0.00")
            return formatter.format(celsius) +" C"
        }
        fun makeSnackBar(context: Context?, msg: String) {
            if (context != null) {
                val snackbar = Snackbar.make(
                    (context as Activity).findViewById(R.id.content),
                    msg + "", Snackbar.LENGTH_SHORT
                )
                val view = snackbar.view
                val tv = view
                    .findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
                tv.setTextColor(Color.WHITE) //change textColor
                val tvAction = view
                    .findViewById<View>(com.google.android.material.R.id.snackbar_action) as TextView
                tvAction.textSize = 16f
                tvAction.setTextColor(Color.WHITE)
                snackbar.show()
            }
        }

        fun convertDateToyyyyddmmToddmmyyyy(dateToFormat: String?): String? {
            val inputFormat: DateFormat = SimpleDateFormat(ConstantUtil.DATE_FORMAT_yyyy_mm_dd)
            val outputFormat: DateFormat = SimpleDateFormat(ConstantUtil.DATE_FORMAT_dd_mm_yy)
            var date: Date? = null
            try {
                if (dateToFormat != null) {
                    date = inputFormat.parse(dateToFormat)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            var outputDateStr: String? = ""
            if (date != null) {
                outputDateStr = outputFormat.format(date)
            }
            outputDateStr = convertDateToddmmyyyyToddMMYY(outputDateStr)
            return outputDateStr
        }

        fun convertDateToddmmyyyyToddMMYY(dateToFormat: String?): String? {
            val inputFormat: DateFormat = SimpleDateFormat(ConstantUtil.DATE_FORMAT_dd_mm_yy)
            val outputFormat: DateFormat = SimpleDateFormat(ConstantUtil.DATE_FORMAT_dd_MMM_yy)
            var date: Date? = null
            try {
                if (dateToFormat != null) {
                    date = inputFormat.parse(dateToFormat)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            var outputDateStr: String? = ""
            if (date != null) {
                outputDateStr = outputFormat.format(date)
            }
            return outputDateStr
        }


        /* fun convertToContentDataListModel(s: String): ContentDataList {
             return Gson().fromJson(s.trim { it <= ' ' }, ContentDataList::class.java)
         }*/
    }


}