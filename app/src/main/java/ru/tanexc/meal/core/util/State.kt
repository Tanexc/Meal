package ru.tanexc.meal.core.util

sealed class State<T>(
    val data: T?
) {
    class Success<T>(data: T? = null) : State<T>(data)

    class Loading<T>(data: T? = null) : State<T>(data)

    class Error<T>(data: T? = null) : State<T>(data)

    class NotStarted<T>(data: T? = null) : State<T>(data)
}