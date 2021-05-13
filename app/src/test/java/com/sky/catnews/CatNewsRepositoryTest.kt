package com.sky.catnews

import com.sky.catnews.dto.NewsDto
import com.sky.catnews.dto.NewsStoryDto
import com.sky.catnews.models.News
import com.sky.catnews.models.NewsStory
import com.sky.catnews.network.NetworkResource
import com.sky.catnews.network.NetworkService
import com.sky.catnews.repository.CatNewsRepository
import com.sky.catnews.repository.CatNewsRepositoryImpl
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

internal class CatNewsRepositoryTest {
    private val networkService: NetworkService = mock()
    private val responseBody: ResponseBody = mock()
    private lateinit var repository: CatNewsRepository

    @Before
    fun setUp() {
        repository = CatNewsRepositoryImpl(networkService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Test Fetch News Should Load And Return News List Data When Success`() {
        runBlocking {
            whenever(networkService.getNews()).thenReturn(Response.success(newsTestData))
            val newsFlow = repository.fetchNews()
            val newsList = mutableListOf<NetworkResource<NewsDto>>()
            newsFlow.toList(newsList)

            assert(newsList.first() is NetworkResource.Loading)
            assert(newsList.last() is NetworkResource.Success)

            val data = (newsList.last() as NetworkResource.Success).data
            assert(!data?.newsData.isNullOrEmpty())
            assert(data?.newsData?.size == 3)
            assert(data?.newsData?.last()?.headline == "Headline 3")
        }
    }

    @Test
    fun `Test Fetch News Should Load And Return Error When Failure`() {
        runBlocking {
            val response: Response<News> = Response.error(404, responseBody)
            whenever(networkService.getNews()).thenReturn(response)
            val newsFlow = repository.fetchNews()
            val newsList = mutableListOf<NetworkResource<NewsDto>>()
            newsFlow.toList(newsList)

            assert(newsList.first() is NetworkResource.Loading)
            assert(newsList.last() is NetworkResource.Error)
        }
    }

    @Test
    fun `Test Fetch Story Should Load And Return Story Data When Success`() {
        runBlocking {
            whenever(networkService.getStory("")).thenReturn(Response.success(newsStoryTestData))
            val newsFlow = repository.fetchStory("")
            val story = mutableListOf<NetworkResource<NewsStoryDto>>()
            newsFlow.toList(story)

            assert(story.first() is NetworkResource.Loading)
            assert(story.last() is NetworkResource.Success)

            val data = (story.last() as NetworkResource.Success).data
            assert(!data?.contents.isNullOrEmpty())
            assert(data?.contents?.size == 3)
            assert(data?.contents?.first()?.type == "paragraph")
            assert(data?.contents?.get(1)?.type == "image")
        }
    }

    @Test
    fun `Test Fetch Story Should Load And Return Error When Failure`() {
        runBlocking {
            val response: Response<NewsStory> = Response.error(404, responseBody)
            whenever(networkService.getStory("")).thenReturn(response)
            val newsFlow = repository.fetchStory("")
            val story = mutableListOf<NetworkResource<NewsStoryDto>>()
            newsFlow.toList(story)

            assert(story.first() is NetworkResource.Loading)
            assert(story.last() is NetworkResource.Error)
        }
    }
}
