package ru.lffq.fmaster.feature_profile.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NutritionTypesModel(
    @SerializedName("categories")
    val categories: List<Type>
) {
    @Keep
    data class Type(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("image")
        val image: String,
    )
}