package com.sky.catnews.di.modules

import android.content.Context
import com.sky.catnews.network.MockNetworkService
import com.sky.catnews.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkService(@ApplicationContext context: Context): NetworkService {
        return MockNetworkService(context)
    }
}
