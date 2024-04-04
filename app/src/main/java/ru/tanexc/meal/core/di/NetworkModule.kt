package ru.tanexc.meal.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.tanexc.meal.presentation.util.other.network.ConnectionObserver
import ru.tanexc.meal.presentation.util.other.network.NetworkConnectionObserver
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideConnectionObserver(
        @ApplicationContext context: Context
    ): ConnectionObserver = NetworkConnectionObserver(context)
}