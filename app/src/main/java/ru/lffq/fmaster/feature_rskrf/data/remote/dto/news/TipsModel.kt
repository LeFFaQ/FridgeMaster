package ru.lffq.fmaster.feature_rskrf.data.remote.dto.news


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TipsModel(
    @SerializedName("message")
    val message: List<Any>,
    @SerializedName("response")
    val response: Response
) {
    @Keep
    data class Response(
        @SerializedName("articles")
        val articles: List<Article>,
        @SerializedName("available_sections")
        val availableSections: List<AvailableSection>
    ) {
        @Keep
        data class Article(
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
            @Keep
            data class Section(
                @SerializedName("id")
                val id: Int,
                @SerializedName("title")
                val title: String
            )
        }

        @Keep
        data class AvailableSection(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }
}