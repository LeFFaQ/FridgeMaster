package ru.lffq.fmaster.feature_rskrf.domain.model.news

data class News(
    val message: List<Any>,
    val response: List<Response>
) {
    data class Response(
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
}
