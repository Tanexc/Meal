package ru.tanexc.meal.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import ru.tanexc.meal.data.local.entity.CategoryEntity
import ru.tanexc.meal.domain.Domain

data class Category(
    @JsonProperty("idCategory") val id: Int,
    @JsonProperty("strCategory") val title: String,
    @JsonProperty("strCategoryThumb") val thumb: String,
    @JsonProperty("strCategoryDescription") val description: String
) : Domain {
    override fun asData(): CategoryEntity = CategoryEntity(
        id, title, thumb, description
    )

}