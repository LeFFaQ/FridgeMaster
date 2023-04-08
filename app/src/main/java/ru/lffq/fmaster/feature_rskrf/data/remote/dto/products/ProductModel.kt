package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductModel(
    @SerializedName("message")
    val message: List<Any>,
    @SerializedName("response")
    val response: Response
) {
    @Keep
    data class Response(
        @SerializedName("category_name")
        val categoryName: String,
        @SerializedName("criteria_ratings")
        val criteriaRatings: List<CriteriaRating>,
        @SerializedName("description")
        val description: String,
        @SerializedName("has_quality_mark")
        val hasQualityMark: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_in_favorites")
        val isInFavorites: Boolean,
        @SerializedName("manufacturer")
        val manufacturer: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("product_documents")
        val productDocuments: List<ProductDocument>,
        @SerializedName("product_info")
        val productInfo: List<ProductInfo>,
        @SerializedName("product_link")
        val productLink: String,
        @SerializedName("recommendations")
        val recommendations: List<Recommendation>,
        @SerializedName("research")
        val research: Research,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("total_rating")
        val totalRating: Int,
        @SerializedName("worth")
        val worth: List<String>
    ) {
        @Keep
        data class CriteriaRating(
            @SerializedName("title")
            val title: String,
            @SerializedName("value")
            val value: Int
        )

        @Keep
        data class ProductDocument(
            @SerializedName("file")
            val file: String,
            @SerializedName("name")
            val name: String
        )

        @Keep
        data class ProductInfo(
            @SerializedName("info")
            val info: String,
            @SerializedName("name")
            val name: String
        )

        @Keep
        data class Recommendation(
            @SerializedName("category_name")
            val categoryName: String,
            @SerializedName("criteria_ratings")
            val criteriaRatings: List<CriteriaRating>,
            @SerializedName("has_bad_quality_mark")
            val hasBadQualityMark: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("manufacturer")
            val manufacturer: String,
            @SerializedName("price")
            val price: String,
            @SerializedName("thumbnail")
            val thumbnail: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("total_rating")
            val totalRating: Int
        ) {
            @Keep
            data class CriteriaRating(
                @SerializedName("title")
                val title: String,
                @SerializedName("value")
                val value: Int
            )
        }

        @Keep
        data class Research(
            @SerializedName("date")
            val date: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: String,
            @SerializedName("product_group")
            val productGroup: String,
            @SerializedName("title")
            val title: String
        )
    }
}