package ru.lffq.fmaster.feature_feed.ui.feed

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.lffq.fmaster.R
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.dataStore
import ru.lffq.fmaster.feature_feed.domain.FeedUseCases
import ru.lffq.fmaster.feature_profile.ui.preferences.DS_NUTRITION_TYPE_STRING_KEY
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.usecase.GetArticleUseCase
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.GetRecipeInformationUseCase
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.RandomRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val useCases: FeedUseCases,
    private val randomRecipeUseCase: RandomRecipeUseCase,

    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val getArticleUseCase: GetArticleUseCase,
    networkConnectionObserver: ConnectionObserver
) : ViewModel() {
    val networkConnection = networkConnectionObserver.observe()

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articles: StateFlow<List<Article>> = _articles
    val sections = _articles.map { articlesFlow ->
        articlesFlow.map { article ->
            article.section
        }.distinctBy { it.title }
    }

    private val foodType = context.dataStore.data.map {
        it[DS_NUTRITION_TYPE_STRING_KEY] ?: ""
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    private val _randomDailyRecipe: MutableStateFlow<Resource<Random.Recipe>> =
        MutableStateFlow(Resource.Loading())
    val randomDailyRecipe = _randomDailyRecipe.asStateFlow()

    private val _content: MutableStateFlow<FeedContent> =
        MutableStateFlow(FeedContent.NoContent)
    val content = _content.asStateFlow()

    val errorChannel = Channel<String>(capacity = UNLIMITED)

    init {
        viewModelScope.launch {
            getArticles()
            getRandomDailyRecipe()
        }
    }

    private suspend fun getArticles() {
        _articles.value = useCases.getArticles(viewModelScope)
    }

    private suspend fun getRandomDailyRecipe() {
        randomRecipeUseCase(
            onSuccess = {
                _randomDailyRecipe.value = it
            },
            onError = { sendErrorToChannel(it.message!!) },
            tags = foodType.value,
            number = 1
        )
    }

    private fun loadRecipeInformation(id: Int) {
        if (_content.value is FeedContent.NoContent) {
            _content.value = FeedContent.RecipeInformation(Resource.Loading())
        }
        viewModelScope.launch {
            getRecipeInformationUseCase(
                onSuccess = { information ->
                    _content.value =
                        FeedContent.RecipeInformation(Resource.Success(data = information))
                },
                onError = { errorMsg ->
                    _content.value =
                        FeedContent.RecipeInformation(Resource.Error(message = errorMsg))
                    sendErrorToChannel(errorMsg)
                },
                id = id
            )

        }
    }

    private fun loadArticle(id: Int) {
        if (_content.value is FeedContent.NoContent) {
            _content.value = FeedContent.ArticleDetails(Resource.Loading())
        }
        viewModelScope.launch {
            getArticleUseCase(
                onSuccess = {
                    _content.value = FeedContent.ArticleDetails(Resource.Success(data = it))
                },
                onError = { errorMsg ->
                    _content.value = FeedContent.ArticleDetails(Resource.Error(message = errorMsg))
                    errorChannel.send(errorMsg)
                },
                id = id
            )
        }
    }

    private suspend fun sendErrorToChannel(message: String) {
        errorChannel.send(message)
    }

    fun onEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.OnArticleClick -> loadArticle(event.id)
            is FeedEvent.OnDailyRecipeClick -> loadRecipeInformation(event.id)
            is FeedEvent.ClearContent -> _content.value = FeedContent.NoContent
        }
    }
}

sealed class FeedEvent {
    data class OnArticleClick(val id: Int) : FeedEvent()
    data class OnDailyRecipeClick(val id: Int) : FeedEvent()

    object ClearContent : FeedEvent()
}

sealed class FeedContent() {
    object NoContent : FeedContent()
    data class RecipeInformation(val information: Resource<Information>) : FeedContent()
    data class ArticleDetails(val details: Resource<Details>) : FeedContent()
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

