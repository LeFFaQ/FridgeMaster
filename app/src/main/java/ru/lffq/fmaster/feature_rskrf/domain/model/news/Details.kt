package ru.lffq.fmaster.feature_rskrf.domain.model.news

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.DetailsModel

data class Details(
    val message: List<Any>,
    val response: Response
) {
    data class Response(
        val articleLink: String,
        val contentBlocks: List<ContentBlock>,
        val date: String,
        val description: String,
        val hints: List<Any>,
        val id: Int,
        val isInFavorites: Boolean,
        val section: Section,
        val thumbnail: String,
        val title: String,
    ) {
        data class ContentBlock(
            val content: String,
            val type: String
        )

        data class Section(
            val id: Int,
            val title: String
        )
    }
}

fun DetailsModel.toDetails(): Details {
    return Details(
        message = this.message,
        response = this.response.let {
            Details.Response(
                articleLink = it.articleLink,
                contentBlocks = it.contentBlocks.map { contentBlock ->
                    Details.Response.ContentBlock(
                        content = contentBlock.content,
                        type = contentBlock.type
                    )
                },
                date = it.date,
                description = it.description,
            )
        }
        )
    )
}