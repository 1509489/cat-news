package com.sky.catnews.ui.storyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sky.catnews.dto.NewsStoryDto
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.repository.CatNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val repository: CatNewsRepository) : ViewModel() {
    fun fetchStory(id: String): LiveData<NetworkResource<NewsStoryDto>> {
        return repository.fetchStory(id)
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
    }
}
