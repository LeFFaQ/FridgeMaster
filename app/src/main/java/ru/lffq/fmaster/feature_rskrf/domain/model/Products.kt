package ru.lffq.fmaster.feature_rskrf.domain.model

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.ProductsModel

data class Products(
    val products: List<Product>
) {
    data class Product(
        val id: Int,
        val title: String,
        val totalRating: Double,
        val price: String,
        val thumbnail: String,
        val criteriaRatings: List<CriteriaRating>,
        val categoryName: String,
        val manufacturer: String,
        val hasQualityMark: Boolean

    )
}

fun ProductsModel.toProducts(): Products {
    return this.response.products.map {
        Products.Product(
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
        Products(
            products = it,
        )
    }
}