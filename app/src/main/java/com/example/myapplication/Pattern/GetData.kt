package com.example.myapplication.Pattern

import retrofit2.Call;
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import retrofit2.http.Query;


interface GetData {
    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int
    ): Call<Headline>

    companion object Factory {
        fun create(): GetData {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
                .build()

            return retrofit.create(GetData::class.java);
        }
    }
}