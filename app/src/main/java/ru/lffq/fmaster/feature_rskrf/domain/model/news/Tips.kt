package ru.lffq.fmaster.feature_rskrf.domain.model.news


data class Tips(
    val message: List<Any>,
    val response: Response
) {
    data class Response(
        val articles: List<Article>,
        val availableSections: List<AvailableSection>
    ) {
        data class Article(
            val date: String,
            val description: String,
            val id: Int,
            val section: Section,
            val thumbnail: String,
            val title: String
        ) {
            data class Section(
                val id: Int,
                val title: String
            )
        }

        data class AvailableSection(
            val id: Int,
            val title: String
        )
    }
}