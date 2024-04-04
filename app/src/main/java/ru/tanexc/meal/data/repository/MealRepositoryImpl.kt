package ru.tanexc.meal.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.tanexc.meal.core.util.PAGE_SIZE
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.data.local.dao.MealDao
import ru.tanexc.meal.data.local.entity.MealEntity
import ru.tanexc.meal.data.remote.MealApi
import ru.tanexc.meal.domain.model.Meal
import ru.tanexc.meal.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDao: MealDao,
    private val mealApi: MealApi
) : MealRepository {
    override fun getMeal(isConnected: Boolean): Flow<State<List<Meal>>> = flow {
        emit(State.Loading())
        val meal: List<MealEntity> = mealDao.getAllMeal()
        if (meal.isEmpty() && isConnected) {
            val data: MutableList<Meal> = mutableListOf()
            repeat(PAGE_SIZE) {
                data.add(mealApi.getMeal().meals[0])
            }
            emit(State.Success(data))
            mealDao.setMeal(data.map { it.asData() })
        } else if (meal.isEmpty()) {
            emit(State.Error())
        } else {
            emit(State.Success(meal.map { it.asDomain() }))
        }
    }

    override fun getMealPage(page: Int, isConnected: Boolean): Flow<State<List<Meal>>> = flow {
        emit(State.Loading())
        val meal: List<MealEntity> = mealDao.getMealPage(page)
        if (meal.isEmpty() && isConnected) {
            val data: MutableList<Meal> = mutableListOf()
            repeat(PAGE_SIZE) {
                emit(State.Success(listOf(mealApi.getMeal().meals[0])))
            }
            mealDao.setMeal(data.map { it.asData() })
        } else if (meal.isEmpty()) {
            emit(State.Error())
        }  else {
            emit(State.Success(meal.map { it.asDomain() }))
        }

    }

    override fun getMealByCategory(
        category: String,
        isConnected: Boolean
    ): Flow<State<List<Meal>>> = flow {
        emit(State.Loading())
        val meal: List<MealEntity> = mealDao.getByCategory(category)
        if (meal.size < PAGE_SIZE && isConnected) {
            val data: MutableList<Meal> = mutableListOf()
            mealApi.getMealsByCategory(category).meals.forEach {
                data.add(mealApi.getById(it.id).meals[0])
                mealDao.setMeal(data.map { it.asData() })
                emit(State.Success(data))
            }
        } else if (meal.isEmpty()) {
            emit(State.Error())
        } else {
            emit(State.Success(meal.map { it.asDomain() }))
        }
    }
}