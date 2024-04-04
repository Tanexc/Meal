package ru.tanexc.meal.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tanexc.meal.domain.model.MealList

interface MealApi {
    @GET("lookup.php")
    suspend fun getById(@Query("i") id: Long): MealList

    @GET("random.php")
    suspend fun getMeal(): MealList

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealList
}