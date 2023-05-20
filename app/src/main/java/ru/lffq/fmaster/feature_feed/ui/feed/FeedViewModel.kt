package ru.lffq.fmaster.feature_feed.ui.feed

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.lffq.fmaster.R
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.feature_feed.domain.FeedUseCases
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.RandomRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val useCases: FeedUseCases,
    private val randomRecipeUseCase: RandomRecipeUseCase,
    networkConnectionObserver: ConnectionObserver
    //private val adsRepository: IAdsRepository
) : ViewModel() {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    val sections = _articles.map { articlesFlow ->
        articlesFlow.map { article ->
            article.section
        }.distinctBy { it.title }
    }

    val networkConnection = networkConnectionObserver.observe()

    private val _randomDailyRecipe: MutableStateFlow<Resource<Random.Recipe>> =
        MutableStateFlow(Resource.Loading())
    val randomDailyRecipe: StateFlow<Resource<Random.Recipe>> = _randomDailyRecipe

    val errorChannel = Channel<String>(capacity = UNLIMITED)

    init {
        Log.d("Feed", "init block")
        //Log.d("FeedVM", "articles is ${articles.value}")
        getArticles()
        getRandomDailyRecipe()
    }

    private fun getArticles() {
        viewModelScope.launch {
            _articles.value = useCases.getArticles(this)
            errorChannel.send("Тестовая ошибка")
        }
    }

    private fun getRandomDailyRecipe() {
        viewModelScope.launch {
            randomRecipeUseCase(
                onSuccess = {
                    _randomDailyRecipe.value = it
                },
                onError = { errorChannel.send(it.message!!) },
                tags = "vegan",
                number = 1
            )
        }
    }

}

data class DetailInformation(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)

sealed class SearchType(@StringRes val title: Int) {
    object Products : SearchType(R.string.search_products_title)
    object Dishes : SearchType(R.string.search_dishes_title)
}

sealed class Suggestion(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val icon: Int
) {
    object Recipe : Suggestion(
        R.string.feed_suggestion_recipe,
        R.string.feed_suggestion_recipe_description,
        R.drawable.ic_checklist_24
    )

    object Product : Suggestion(
        R.string.feed_suggestion_product,
        R.string.feed_suggestion_product_description,
        R.drawable.ic_fastfood_24
    )
}

