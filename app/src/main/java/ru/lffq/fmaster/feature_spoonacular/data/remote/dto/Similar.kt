package ru.lffq.fmaster.feature_spoonacular.data.remote.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

class Similar : ArrayList<Similar.SimilarItem>() {
    @Keep
    data class SimilarItem(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("imageType")
        val imageType: String? = null,
        @SerializedName("readyInMinutes")
        val readyInMinutes: Int? = null,
        @SerializedName("servings")
        val servings: Int? = null,
        @SerializedName("sourceUrl")
        val sourceUrl: String? = null,
        @SerializedName("title")
        val title: String? = null
    )
}