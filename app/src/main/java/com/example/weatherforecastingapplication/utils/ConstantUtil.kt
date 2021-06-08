package  com.example.weatherforecastingapplication.utils

class ConstantUtil{
    companion object{
        val connectionTimeOut: Long = 120; // Room Database API call time
        val serverSuccessCode:Int = 200;

        const val DATE_FORMAT_yyyy_mm_dd = "yyyy-MM-dd"
        const val DATE_FORMAT_dd_mm_yy = "dd-MM-yy"
        const val DATE_FORMAT_dd_MMM_yy = "dd MMM yy"
        const val API_KEY = "f9e8c9967267134c844acec8b1e909a5"
    }
}