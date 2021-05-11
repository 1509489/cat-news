package com.sky.catnews.ui.newslistscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sky.catnews.dto.NewsDto
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.repository.CatNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: CatNewsRepository
) : ViewModel() {

    fun fetchNews(): LiveData<NetworkResource<NewsDto>> {
        return repository.fetchNews().asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
    }
}
