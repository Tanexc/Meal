package ru.tanexc.meal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanexc.meal.domain.Data
import ru.tanexc.meal.domain.model.Meal

@Entity
data class MealEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val drinkAlternate: String?,
    val category: String,
    val area: String,
    val instructions: String,
    val thumb: String,
    val tags: String?,
    val youtubeLink: String,
    val ingredient1: String,
    val ingredient2: String,
    val ingredient3: String,
    val ingredient4: String,
    val ingredient5: String,
    val ingredient6: String,
    val ingredient7: String,
    val ingredient8: String,
    val ingredient9: String,
    val ingredient10: String,
    val ingredient11: String,
    val ingredient12: String,
    val ingredient13: String,
    val ingredient14: String,
    val ingredient15: String,
    val ingredient16: String,
    val ingredient17: String,
    val ingredient18: String,
    val ingredient19: String,
    val ingredient20: String,
    val measure1: String,
    val measure2: String,
    val measure3: String,
    val measure4: String,
    val measure5: String,
    val measure6: String,
    val measure7: String,
    val measure8: String,
    val measure9: String,
    val measure10: String,
    val measure11: String,
    val measure12: String,
    val measure13: String,
    val measure14: String,
    val measure15: String,
    val measure16: String,
    val measure17: String,
    val measure18: String,
    val measure19: String,
    val measure20: String,
    val sourceLink: String,
    val imageSource: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: Long?
) : Data {
    override fun asDomain(): Meal = Meal(
        id,
        title,
        drinkAlternate,
        category,
        area,
        instructions,
        thumb,
        tags,
        youtubeLink,
        ingredient1,
        ingredient2,
        ingredient3,
        ingredient4,
        ingredient5,
        ingredient6,
        ingredient7,
        ingredient8,
        ingredient9,
        ingredient10,
        ingredient11,
        ingredient12,
        ingredient13,
        ingredient14,
        ingredient15,
        ingredient16,
        ingredient17,
        ingredient18,
        ingredient19,
        ingredient20,
        measure1,
        measure2,
        measure3,
        measure4,
        measure5,
        measure6,
        measure7,
        measure8,
        measure9,
        measure10,
        measure11,
        measure12,
        measure13,
        measure14,
        measure15,
        measure16,
        measure17,
        measure18,
        measure19,
        measure20,
        sourceLink,
        imageSource,
        creativeCommonsConfirmed,
        dateModified
    )
}