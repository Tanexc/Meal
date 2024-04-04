package ru.tanexc.meal.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.tanexc.meal.data.local.dao.CategoryDao
import ru.tanexc.meal.data.local.dao.MealDao
import ru.tanexc.meal.data.local.entity.CategoryEntity
import ru.tanexc.meal.data.local.entity.MealEntity

@Database(
    entities = [CategoryEntity::class, MealEntity::class],
    exportSchema = false,
    version = 1,
)
abstract class MealDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val mealDao: MealDao
}