package ru.tanexc.meal.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.domain.model.Meal

interface MealRepository {
    fun getMeal(isConnected: Boolean): Flow<State<List<Meal>>>

    fun getMealPage(page: Int, isConnected: Boolean): Flow<State<List<Meal>>>

    fun getMealByCategory(category: String, isConnected: Boolean): Flow<State<List<Meal>>>
}