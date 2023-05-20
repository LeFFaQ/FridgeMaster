package ru.lffq.fmaster.feature_rskrf.domain.model.products

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.products.SubcategoriesModel

data class Subcategory(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val icon: String
)

fun SubcategoriesModel.toSubcategories(): List<Subcategory> {
    return this.response.map {
        Subcategory(it.id, it.title, it.thumbnail, it.icon)
    }
}
