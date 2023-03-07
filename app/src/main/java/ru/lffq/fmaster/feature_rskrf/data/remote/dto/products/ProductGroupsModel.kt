package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import com.google.gson.annotations.SerializedName

data class ProductGroupsModel(
    @SerializedName("response")
    val response: Response,
    @SerializedName("message")
    val message: List<Any>
) {
    data class Response(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("productGroups")
        val productGroups: List<ProductGroup>,
        @SerializedName("total_reviewed_products")
        val totalReviewedProducts: Int
    ) {
        data class ProductGroup(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("thumbnail")
            val thumbnail: String,
            @SerializedName("quality_marks")
            val qualityMarks: Int,
            @SerializedName("bad_quality_products")
            val badQualityProducts: Int
        )
    }
}