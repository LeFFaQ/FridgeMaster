package ru.lffq.fmaster.feature_spoonacular.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Search(
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val results: List<Result?>? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
) {
    @Keep
    data class Result(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("image")
        val image: String? = null,
        @SerializedName("imageType")
        val imageType: String? = null,
        @SerializedName("nutrition")
        val nutrition: Nutrition? = null,
        @SerializedName("title")
        val title: String? = null
    ) {
        @Keep
        data class Nutrition(
            @SerializedName("nutrients")
            val nutrients: List<Nutrient?>? = null
        ) {
            @Keep
            data class Nutrient(
                @SerializedName("amount")
                val amount: Double? = null,
                @SerializedName("name")
                val name: String? = null,
                @SerializedName("unit")
                val unit: String? = null
            )
        }
    }
}