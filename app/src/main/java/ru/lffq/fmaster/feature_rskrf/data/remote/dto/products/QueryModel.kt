package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class QueryModel(
    @SerializedName("response")
    val response: Response,
    @SerializedName("message")
    val message: List<Any>
) {
    @Keep
    data class Response(
        @SerializedName("total_items")
        val totalItems: Int,
        @SerializedName("products")
        val products: List<Product>
    ) {
        @Keep
        data class Product(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("total_rating")
            val totalRating: Double,
            @SerializedName("price")
            val price: String,
            @SerializedName("thumbnail")
            val thumbnail: String,
            @SerializedName("criteria_ratings")
            val criteriaRatings: List<CriteriaRating>,
            @SerializedName("category_name")
            val categoryName: String,
            @SerializedName("manufacturer")
            val manufacturer: String,
            @SerializedName("has_quality_mark")
            val hasQualityMark: Boolean
        ) {
            @Keep
            data class CriteriaRating(
                @SerializedName("title")
                val title: String,
                @SerializedName("value")
                val value: Double
            )
        }
    }
}