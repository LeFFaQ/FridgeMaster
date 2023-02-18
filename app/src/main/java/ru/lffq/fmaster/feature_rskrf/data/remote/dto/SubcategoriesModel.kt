package ru.lffq.fmaster.feature_rskrf.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SubcategoriesModel(
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("message")
    val message: List<Any>
) {
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