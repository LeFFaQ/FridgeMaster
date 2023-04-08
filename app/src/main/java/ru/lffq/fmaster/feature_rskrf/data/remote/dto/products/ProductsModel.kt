package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductsModel(
    @SerializedName("response")
    val response: Response,
    @SerializedName("message")
    val message: List<Any>
) {
    @Keep
    data class Response(
        @SerializedName("research")
        val research: Research,
        @SerializedName("available_sorting")
        val availableSorting: List<AvailableSorting>,
        @SerializedName("available_filters")
        val availableFilters: List<Any>,
        @SerializedName("current_sorting")
        val currentSorting: CurrentSorting,
        @SerializedName("products")
        val products: List<Product>
    ) {
        @Keep
        data class Research(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("image")
            val image: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("product_group")
            val productGroup: String,
            @SerializedName("product_group_id")
            val productGroupId: String
        )

        @Keep
        data class AvailableSorting(
            @SerializedName("type")
            val type: String,
            @SerializedName("value")
            val value: String,
            @SerializedName("title")
            val title: String
        )

        @Keep
        data class CurrentSorting(
            @SerializedName("type")
            val type: String,
            @SerializedName("value")
            val value: String,
            @SerializedName("title")
            val title: String
        )

        @Keep
        data class Product(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("total_rating")
            val totalRating: Double,
            @SerializedName("manufacturer")
            val manufacturer: String,
            @SerializedName("price")
            val price: String,
            @SerializedName("thumbnail")
            val thumbnail: String,
            @SerializedName("has_quality_mark")
            val hasQualityMark: Boolean,
            @SerializedName("criteria_ratings")
            val criteriaRatings: List<CriteriaRating>,
            @SerializedName("category_name")
            val categoryName: String,
            @SerializedName("has_bad_quality_mark")
            val hasBadQualityMark: Boolean
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