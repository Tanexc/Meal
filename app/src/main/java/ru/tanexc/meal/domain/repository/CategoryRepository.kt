package ru.tanexc.meal.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.domain.model.Category

interface CategoryRepository {
    fun getCategories(isConnected: Boolean): Flow<State<List<Category>>>
}