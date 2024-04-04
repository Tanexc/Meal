package ru.tanexc.meal.presentation.screen.menuscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.tanexc.meal.core.util.State
import ru.tanexc.meal.domain.model.Category
import ru.tanexc.meal.domain.model.Meal
import ru.tanexc.meal.domain.repository.CategoryRepository
import ru.tanexc.meal.domain.repository.MealRepository
import ru.tanexc.meal.presentation.util.other.network.ConnectionObserver
import ru.tanexc.meal.presentation.util.other.network.NetworkStatus
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val categoryRepository: CategoryRepository,
    private val networkObserver: ConnectionObserver
) : ViewModel() {
    private val _loadingState: MutableState<State<Meal?>> = mutableStateOf(State.NotStarted())
    val loadingState by _loadingState

    private val _categoryLoadingState: MutableState<State<Category?>> =
        mutableStateOf(State.NotStarted())
    val categoryLoading by _categoryLoadingState

    private val _pageLoadingState: MutableState<State<Meal?>> = mutableStateOf(State.NotStarted())
    val pageLoadingState by _pageLoadingState

    private val _categories: MutableState<List<Category>> = mutableStateOf(emptyList())
    val category by _categories

    private val _meals: MutableState<List<Meal>> = mutableStateOf(emptyList())
    val meals by _meals

    private val _networkStatus: MutableState<NetworkStatus> = mutableStateOf(NetworkStatus.Lost)
    val networkStatus by _networkStatus

    private val _selectedCategory: MutableState<String?> = mutableStateOf(null)
    val selectedCategory by _selectedCategory

    private var mealJob: Job? = null
    private var categoryJob: Job? = null

    fun init() {
        categoryInit()
        mealInit()
        viewModelScope.launch(Dispatchers.IO) {
            networkObserver.observe().collect { status ->
                _networkStatus.value = status
                categoryInit()
                if (selectedCategory != null) {
                    getMealByCategory(selectedCategory!!)
                } else {
                    mealInit()
                }

            }
        }
    }

    private fun categoryInit() {
        categoryJob?.cancel()
        categoryJob = viewModelScope.launch(Dispatchers.IO) {
            categoryRepository
                .getCategories(isConnected = networkStatus == NetworkStatus.Connected)
                .collect { state ->
                    when (state) {
                        is State.Success -> {
                            _categories.value = state.data ?: emptyList()
                            _categoryLoadingState.value = State.Success()
                        }

                        is State.Loading -> _categoryLoadingState.value = State.Loading()
                        is State.NotStarted -> _categoryLoadingState.value = State.NotStarted()
                        is State.Error -> _categoryLoadingState.value = State.Error()
                    }
                }
        }

    }

    fun mealInit() {
        mealJob?.cancel()
        mealJob = viewModelScope.launch(Dispatchers.IO) {
            mealRepository
                .getMealPage(
                    page = 1,
                    isConnected = networkStatus == NetworkStatus.Connected
                )
                .collect { state ->
                    when (state) {
                        is State.Success -> {
                            _meals.value = state.data ?: emptyList()
                            _loadingState.value = State.Success()
                        }

                        is State.Loading -> _loadingState.value = State.Loading()
                        is State.NotStarted -> _loadingState.value = State.NotStarted()
                        is State.Error -> _loadingState.value = State.Error()
                    }
                }
        }
    }

    fun getMealByCategory(category: String) {
        mealJob?.cancel()
        mealJob = viewModelScope.launch(Dispatchers.IO) {
            mealRepository
                .getMealByCategory(
                    category = category,
                    isConnected = networkStatus == NetworkStatus.Connected
                )
                .collect { state ->
                    when (state) {
                        is State.Success -> {
                            _meals.value = state.data ?: emptyList()
                            _loadingState.value = State.Success()
                        }

                        is State.Loading -> _loadingState.value = State.Loading()
                        is State.NotStarted -> _loadingState.value = State.NotStarted()
                        is State.Error -> _loadingState.value = State.Error()
                    }
                }
        }
    }

    fun getMealPage(page: Int) {
        mealJob?.cancel()
        mealJob = viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getMealPage(
                page = page,
                isConnected = networkStatus == NetworkStatus.Connected
            ).collect { state ->
                when (state) {
                    is State.Success -> {
                        _meals.value += (state.data ?: emptyList())
                        _meals.value = _meals.value.toSet().toList()
                        _loadingState.value = State.Success()
                        _pageLoadingState.value = State.Success()
                    }

                    else -> {
                        _pageLoadingState.value = State.Loading()
                    }
                }
            }
        }
    }

}