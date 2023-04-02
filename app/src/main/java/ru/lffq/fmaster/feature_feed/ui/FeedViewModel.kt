package ru.lffq.fmaster.feature_feed.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.lffq.fmaster.feature_feed.domain.FeedUseCases
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.ui.components.ClosableLayout
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val useCases: FeedUseCases
) : ViewModel() {

    private val _layout: MutableState<FeedLayout> = mutableStateOf(FeedLayout.MainLayout);
    val currentLayout: State<FeedLayout> = _layout

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        Log.d("Feed", "init block")
        //Log.d("FeedVM", "articles is ${articles.value}")
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            _articles.value = useCases.getArticles(this)
        }
    }
}


sealed class FeedLayout(val title: String) {
    //todo: придумать что-нибудь пооригинальнее для заголовка таб-бара
    object MainLayout : FeedLayout("Feed")

    // и сюда тоже
    data class ArticleLayout(
        val id: Int? = null,
        val onCloseLayout: () -> Unit
    ) : FeedLayout(""), ClosableLayout {
        override fun close() = onCloseLayout()
    }
}