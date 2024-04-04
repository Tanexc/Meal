package ru.tanexc.meal.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryList(
    @JsonProperty("categories") val categories: List<Category>
)