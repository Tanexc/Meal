package ru.tanexc.meal.data.remote

import retrofit2.http.GET
import ru.tanexc.meal.domain.model.CategoryList

interface CategoryApi {

    @GET("categories.php")
    suspend fun getCategories(): CategoryList

}