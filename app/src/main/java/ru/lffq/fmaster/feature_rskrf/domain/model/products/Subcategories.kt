package ru.lffq.fmaster.feature_rskrf.domain.model.products

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.products.SubcategoriesModel

data class Subcategories(
    val response: List<Response>,
) {
    data class Response(
        val id: Int,
        val title: String,
        val thumbnail: String,
    )

}

fun SubcategoriesModel.toSubcategories(): Subcategories {
    return this.response.map {
        Subcategories.Response(
            id = it.id,
            title = it.title,
            thumbnail = it.thumbnail,
        )
    }.let {
        Subcategories(
            response = it,
        )
    }
}
