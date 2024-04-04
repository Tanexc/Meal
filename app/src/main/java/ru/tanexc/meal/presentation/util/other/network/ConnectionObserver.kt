package ru.tanexc.meal.presentation.util.other.network

import kotlinx.coroutines.flow.Flow

interface ConnectionObserver {
    fun observe(): Flow<NetworkStatus>
}