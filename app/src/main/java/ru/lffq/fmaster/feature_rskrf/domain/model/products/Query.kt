package ru.lffq.fmaster.feature_rskrf.domain.model.products

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.products.QueryModel

data class Query(
    val response: List<Product>,
) {
    data class Product(
        val id: Int,
        val title: String,
        val totalRating: Double,
        val price: String,
        val thumbnail: String,
        val criteriaRatings: List<CriteriaRating>,
        val categoryName: String,
        val manufacturer: String? = null,
        val hasQualityMark: Boolean
    )
}

fun QueryModel.toQuery(): Query {
    return this.response.products.map {
        Query.Product(
            id = it.id,
            title = it.title,
            totalRating = it.totalRating,
            price = it.price,
            thumbnail = it.thumbnail,
            criteriaRatings = it.criteriaRatings.map { criteriaRating ->
                CriteriaRating(
                    title = criteriaRating.title,
                    value = criteriaRating.value
                )
            },
            categoryName = it.categoryName,
            manufacturer = it.manufacturer,
            hasQualityMark = it.hasQualityMark
        )
    }.let {
        Query(
            response = it,
        )
    }
}