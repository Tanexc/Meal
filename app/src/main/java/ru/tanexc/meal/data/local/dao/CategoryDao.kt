package ru.tanexc.meal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.tanexc.meal.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categoryentity")
    suspend fun getCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCategories(categories: List<CategoryEntity>)
}