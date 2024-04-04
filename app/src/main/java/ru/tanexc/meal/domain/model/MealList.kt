package ru.tanexc.meal.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MealList(
    @JsonProperty("meals") val meals: List<Meal>
)