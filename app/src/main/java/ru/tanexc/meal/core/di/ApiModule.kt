package ru.tanexc.meal.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.tanexc.meal.data.remote.CategoryApi
import ru.tanexc.meal.data.remote.MealApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideCategoryApi(
        retrofit: Retrofit
    ) = retrofit.create(CategoryApi::class.java)

    @Provides
    @Singleton
    fun provideMealApi(
        retrofit: Retrofit
    ) = retrofit.create(MealApi::class.java)
}