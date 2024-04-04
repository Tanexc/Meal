package ru.tanexc.meal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.tanexc.meal.domain.Data
import ru.tanexc.meal.domain.model.Category

@Entity
class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val thumb: String,
    val description: String
) : Data {
    override fun asDomain(): Category = Category(
        id, title, thumb, description
    )
}