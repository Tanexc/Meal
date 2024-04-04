package ru.tanexc.meal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.tanexc.meal.core.util.PAGE_SIZE
import ru.tanexc.meal.data.local.entity.MealEntity

@Dao
interface MealDao {

    @Query("SELECT * FROM mealentity")
    suspend fun getAllMeal(): List<MealEntity>

    @Query("SELECT * FROM mealentity LIMIT $PAGE_SIZE OFFSET :offset")
    suspend fun getMealPage(offset: Int): List<MealEntity>

    @Query("SELECT * FROM mealentity WHERE category = :category")
    suspend fun getByCategory(category: String): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setMeal(meals: List<MealEntity>)
}