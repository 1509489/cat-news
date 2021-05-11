package com.sky.catnews.network

import com.sky.catnews.models.News
import com.sky.catnews.models.NewsStory
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/news-list")
    suspend fun getNews(): Response<News>

    @GET("/story/{id}")
    suspend fun getStory(id: String): Response<NewsStory>
}