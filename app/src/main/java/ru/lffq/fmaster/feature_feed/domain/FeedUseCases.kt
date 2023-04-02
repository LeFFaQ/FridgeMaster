package ru.lffq.fmaster.feature_feed.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.domain.IRskrfRepository
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details

class FeedUseCases(private val repository: IRskrfRepository) {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    suspend fun getArticles(scope: CoroutineScope): List<Article> {
        var news: List<Article>? = null
        var tips: List<Article>? = null

        if (_articles.value.isEmpty()) {

            news = withContext(scope.coroutineContext) {
                repository.getNews().data ?: emptyList()
            }
            tips = withContext(scope.coroutineContext) {
                repository.getTips().data ?: emptyList()
            }
            _articles.value = (news + tips).shuffled()
        }

        return articles.value
    }

    suspend fun getDetails(id: Int): Resource<Details> {
        return repository.getDetails(id)
    }
}