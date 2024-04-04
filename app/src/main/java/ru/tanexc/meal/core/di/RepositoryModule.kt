package ru.tanexc.meal.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tanexc.meal.data.local.database.MealDatabase
import ru.tanexc.meal.data.remote.CategoryApi
import ru.tanexc.meal.data.remote.MealApi
import ru.tanexc.meal.data.repository.CategoryRepositoryImpl
import ru.tanexc.meal.data.repository.MealRepositoryImpl
import ru.tanexc.meal.data.repository.SettingsRepositoryImpl
import ru.tanexc.meal.domain.repository.CategoryRepository
import ru.tanexc.meal.domain.repository.MealRepository
import ru.tanexc.meal.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepository(
        database: MealDatabase,
        mealApi: MealApi
    ): MealRepository = MealRepositoryImpl(database.mealDao, mealApi)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        database: MealDatabase,
        categoryApi: CategoryApi
    ): CategoryRepository = CategoryRepositoryImpl(database.categoryDao, categoryApi)

    @Provides
    @Singleton
    fun provideSettingsRepository(
        dataStore: DataStore<Preferences>
    ): SettingsRepository = SettingsRepositoryImpl(dataStore)


}