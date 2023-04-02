package ru.lffq.fmaster.feature_rskrf.domain.model.news

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.DetailsModel

data class Details(
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


fun DetailsModel.toDetails(): Details {
    return this.response.let {
        Details(
            articleLink = it.articleLink,
            contentBlocks = it.contentBlocks.map { contentBlock ->
                Details.ContentBlock(
                    content = contentBlock.content,
                    type = contentBlock.type
                )
            },
            date = it.date,
            description = it.description,
            hints = it.hints,
            id = it.id,
            isInFavorites = it.isInFavorites,
            section = it.section.let { section ->
                Details.Section(
                    id = section.id,
                    title = section.title
                )
            },
            thumbnail = it.thumbnail,
            title = it.title
        )
    }
}

