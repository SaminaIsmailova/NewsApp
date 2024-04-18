package com.example.newsapp.data.server

import com.example.newsapp.data.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("/api/1/news")
    suspend fun getNews(
        @Query("country") country: String = "kg",
        @Query("apikey") apiKey: String = "pub_4217777697cf2c228c59e6d1d71ae2484ea27"
    ): Response<NewsModel>
}