package com.denave.contentrepo.data.remote

import com.example.weatherforecastingapplication.data.remote.ApiEndPoint
import com.example.weatherforecastingapplication.utils.ConstantUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
           // val logging = HttpLoggingInterceptor()
            //logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
           // client.addInterceptor(logging)
            client.connectTimeout(ConstantUtil.connectionTimeOut, TimeUnit.SECONDS)
                    .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(ApiEndPoint.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client.build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return retrofit
        }
}