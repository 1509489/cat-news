package com.sky.catnews.network

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.sky.catnews.R
import com.sky.catnews.models.News
import com.sky.catnews.models.NewsStory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class MockNetworkService @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkService {
    override suspend fun getNews(): Response<News> {
        val mockNewsResponse = getJsonResponse(
            News::class.java,
            loadJSONFromResource(R.raw.sample_list)
        )
        // A delay to simulate network call
        delay(2000L)
        return Response.success(mockNewsResponse)
    }

    override suspend fun getStory(id: String): Response<NewsStory> {
        val mockStoryResponse = getJsonResponse(
            NewsStory::class.java,
            loadJSONFromResource(R.raw.sample_story1)
        )
        // A delay to simulate network call
        delay(2000L)
        return Response.success(mockStoryResponse)
    }

    private fun loadJSONFromResource(
        @RawRes jsonFile: Int
    ): String {
        return try {
            val inputStream: InputStream = context.resources.openRawResource(jsonFile)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
    }

    private fun <T : Any> getJsonResponse(responseObject: Class<T>, jsonString: String): T? {
        return Gson().fromJson(jsonString, responseObject)
    }
}
