package ru.lffq.fmaster.feature_rskrf.data.remote.dto.news


import com.google.gson.annotations.SerializedName

data class DetailsModel(
    @SerializedName("message")
    val message: List<Any>,
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("article_link")
        val articleLink: String,
        @SerializedName("comments")
        val comments: List<Any>,
        @SerializedName("content_blocks")
        val contentBlocks: List<ContentBlock>,
        @SerializedName("date")
        val date: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("hints")
        val hints: List<Any>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_in_favorites")
        val isInFavorites: Boolean,
        @SerializedName("section")
        val section: Section,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("total_comments")
        val totalComments: Int
    ) {
        data class ContentBlock(
            @SerializedName("content")
            val content: String,
            @SerializedName("type")
            val type: String
        )

        data class Section(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }
}