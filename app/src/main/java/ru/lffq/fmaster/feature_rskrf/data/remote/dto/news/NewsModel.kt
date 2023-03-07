package ru.lffq.fmaster.feature_rskrf.data.remote.dto.news


import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("message")
    val message: List<Any>,
    @SerializedName("response")
    val response: List<Response>
) {
    data class Response(
        @SerializedName("date")
        val date: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("section")
        val section: Section,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String
    ) {
        data class Section(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }
}