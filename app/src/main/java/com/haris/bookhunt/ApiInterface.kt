package com.haris.bookhunt

import com.haris.bookhunt.models.SearchResponse
import com.haris.bookhunt.models.TrendingBooksListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        val BASE_URL = "https://openlibrary.org"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    @GET("/trending/forever.json")
    fun getBooksTrendingAllTime(): Call<TrendingBooksListResponse>

    @GET("/trending/daily.json")
    fun getBooksTrendingToday(): Call<TrendingBooksListResponse>

    @GET("/search.json")
    fun search(@Query("q") query: String): Call<SearchResponse>
}