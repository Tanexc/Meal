package ru.tanexc.meal.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.data.local.dao.CategoryDao
import ru.tanexc.meal.data.remote.CategoryApi
import ru.tanexc.meal.domain.model.Category
import ru.tanexc.meal.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryApi: CategoryApi
) : CategoryRepository {
    override fun getCategories(isConnected: Boolean): Flow<State<List<Category>>> = flow {
        emit(State.Loading())
        var categories: List<Category> = categoryDao.getCategories().map { it.asDomain() }
        if (categories.isEmpty() && isConnected) {
            categories = categoryApi.getCategories().categories
            categoryDao.setCategories(categories.map { it.asData() })
        }
        if (categories.isEmpty()) emit(State.Error())
        else emit(State.Success(categories))
    }
}