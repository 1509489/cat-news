package com.sky.catnews.repository

import com.sky.catnews.dto.NewsDto
import com.sky.catnews.dto.NewsStoryDto
import com.sky.catnews.dto.toNewsDto
import com.sky.catnews.dto.toNewsStoryDto
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.network.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatNewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : CatNewsRepository {
    override fun fetchNews(): Flow<NetworkResource<NewsDto>> {
        return flow {
            emit(NetworkResource.Loading)

            try {
                val response = networkService.getNews()
                when {
                    response.isSuccessful -> {
                        emit(NetworkResource.Success(response.body()?.toNewsDto()))
                    }
                    else -> emit(NetworkResource.Error(Exception(response.errorBody()?.toString())))
                }
            } catch (ex: Exception) {
                emit(NetworkResource.Error(ex))
            }
        }
    }

    override fun fetchStory(id: String): Flow<NetworkResource<NewsStoryDto>> {
        return flow {
            emit(NetworkResource.Loading)

            try {
                val response = networkService.getStory(id)
                when {
                    response.isSuccessful -> {
                        emit(NetworkResource.Success(response.body()?.toNewsStoryDto()))
                    }
                    else -> emit(NetworkResource.Error(Exception(response.errorBody()?.toString())))
                }
            } catch (ex: Exception) {
                emit(NetworkResource.Error(ex))
            }
        }
    }
}
