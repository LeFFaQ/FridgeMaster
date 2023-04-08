package ru.lffq.fmaster.feature_rskrf.data.remote.dto.products


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SubcategoriesModel(
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("message")
    val message: List<Any>
) {
    @Keep
    data class Response(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("thumbnail")
        val thumbnail: String
    )
}