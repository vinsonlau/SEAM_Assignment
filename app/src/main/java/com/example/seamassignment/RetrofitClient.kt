package com.example.seamassignment

import com.example.seamassignment.api.CustomerAPI
import com.example.seamassignment.api.CustomerOrderAPI
import com.example.seamassignment.api.ProductAPI
import com.example.seamassignment.api.StaffAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.0.156:8080/api/"
    //private const val BASE_URL = "http://175.137.228.51:8080/api/"


    val customerOrderInstance: CustomerOrderAPI by lazy {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(CustomerOrderAPI::class.java)
    }

    val productInstance: ProductAPI by lazy {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ProductAPI::class.java)
    }

    val customerInstance: CustomerAPI by lazy {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(CustomerAPI::class.java)
    }

    val staffInstance: StaffAPI by lazy {

        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(StaffAPI::class.java)
    }
}