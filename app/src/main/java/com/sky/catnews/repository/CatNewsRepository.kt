package com.sky.catnews.repository

import com.sky.catnews.dto.NewsDto
import com.sky.catnews.dto.NewsStoryDto
import com.sky.catnews.network.NetworkResource
import kotlinx.coroutines.flow.Flow

interface CatNewsRepository {
    fun fetchNews(): Flow<NetworkResource<NewsDto>>
    fun fetchStory(id: String): Flow<NetworkResource<NewsStoryDto>>
}
