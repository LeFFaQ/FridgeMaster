package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("message")
    val message: List<Any>,
    @SerializedName("response")
    val response: Response
) {
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
        data class CriteriaRating(
            @SerializedName("title")
            val title: String,
            @SerializedName("value")
            val value: Int
        )

        data class ProductDocument(
            @SerializedName("file")
            val file: String,
            @SerializedName("name")
            val name: String
        )

        data class ProductInfo(
            @SerializedName("info")
            val info: String,
            @SerializedName("name")
            val name: String
        )

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
            data class CriteriaRating(
                @SerializedName("title")
                val title: String,
                @SerializedName("value")
                val value: Int
            )
        }

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