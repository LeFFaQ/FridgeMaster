package ru.lffq.fmaster.feature_rskrf.domain.model.products

import com.google.gson.annotations.SerializedName
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.products.ProductModel
import ru.lffq.fmaster.feature_rskrf.domain.model.CriteriaRating

//i don't care

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val categoryName: String,
    val criteriaRatings: List<CriteriaRating>,
    val hasQualityMark: Boolean,
    val isInFavorites: Boolean,
    val productDocuments: List<ProductDocument>,
    val productInfo: List<ProductInfo>,
    val recommendations: List<Recommendation>,
    val research: Research,
    val thumbnail: String,
    val totalRating: Int,
    val worth: List<String>
) {
    data class ProductDocument(
        val file: String,
        val name: String
    )

    data class ProductInfo(
        @SerializedName("info")
        val info: String,
        val name: String
    )

    data class Recommendation(
        val categoryName: String,
        val criteriaRatings: List<CriteriaRating>,
        val hasBadQualityMark: Boolean,
        val id: Int,
        val manufacturer: String,
        val price: String,
        val thumbnail: String,
        val title: String,
        val totalRating: Int
    )

    data class Research(
        val date: String,
        val id: Int,
        val image: String,
        val productGroup: String,
        val title: String
    )
}

fun ProductModel.toProduct() : Product {
    return this.response.let {
        Product(
            id = it.id,
            title = it.title,
            description = it.description,
            manufacturer = it.manufacturer,
            price = it.price,
            categoryName = it.categoryName,
            criteriaRatings = it.criteriaRatings.map { criteriaRating ->
                CriteriaRating(
                    title = criteriaRating.title,
                    value = criteriaRating.value.toDouble()
                )
            },
            hasQualityMark = it.hasQualityMark,
            isInFavorites = it.isInFavorites,
            productDocuments = it.productDocuments.map { productDocument ->
                Product.ProductDocument(
                    file = productDocument.file,
                    name = productDocument.name
                )
            },
            productInfo = it.productInfo.map { productInfo ->
                Product.ProductInfo(
                    info = productInfo.info,
                    name = productInfo.name
                )
            },
            recommendations = it.recommendations.map { recommendation ->
                Product.Recommendation(
                    categoryName = recommendation.categoryName,
                    criteriaRatings = recommendation.criteriaRatings.map { criteriaRating ->
                        CriteriaRating(
                            title = criteriaRating.title,
                            value = criteriaRating.value.toDouble()
                        )
                    },
                    hasBadQualityMark = recommendation.hasBadQualityMark,
                    id = recommendation.id,
                    manufacturer = recommendation.manufacturer,
                    price = recommendation.price,
                    thumbnail = recommendation.thumbnail,
                    title = recommendation.title,
                    totalRating = recommendation.totalRating
                )
            },
            research = Product.Research(
                date = it.research.date,
                id = it.research.id,
                image = it.research.image,
                productGroup = it.research.productGroup,
                title = it.research.title
            ),
            thumbnail = it.thumbnail,
            totalRating = it.totalRating,
            worth = it.worth
        )
    }
}


