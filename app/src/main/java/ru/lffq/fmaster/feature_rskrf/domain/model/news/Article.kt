package ru.lffq.fmaster.feature_rskrf.domain.model.news

import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.NewsModel
import ru.lffq.fmaster.feature_rskrf.data.remote.dto.news.TipsModel

data class Article(
    val date: String,
    val description: String,
    val id: Int,
    val section: Section,
    val thumbnail: String,
    val title: String
) : IArticle {
    data class Section(
        val id: Int,
        val title: String
    )
}

//Костыль, при получении нужно будет
//добавлять этот список в список в вьюмодели
fun NewsModel.toArticle(): List<Article> {
    return this.response.map {
        Article(
            date = it.date,
            description = it.description,
            id = it.id,
            section = Article.Section(
                id = it.id,
                title = it.section.title
            ),
            thumbnail = it.thumbnail,
            title = it.title
        )
    }
}

// тот же самый костыль, что изложен выше
// если что он нужен чтобы избавиться от двух практически
// идентичных классов Tips и News
fun TipsModel.toArticle(): List<Article> {
    return this.response.articles.map {
        Article(
            date = it.date,
            description = it.description,
            id = it.id,
            section = it.section.let { section ->
                Article.Section(id = section.id, title = section.title)
            },
            thumbnail = it.thumbnail,
            title = it.title
        )
    }
}

//Это нужно для placeholder'ов
object EmptyArticle : IArticle

interface IArticle