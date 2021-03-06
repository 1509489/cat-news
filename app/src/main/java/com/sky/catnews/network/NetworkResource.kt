package com.sky.catnews.network

sealed class NetworkResource<out T> {
    data class Success<T>(val data: T?): NetworkResource<T>()
    data class Error(val exception: Exception): NetworkResource<Nothing>()
    object Loading : NetworkResource<Nothing>()
}