package ru.lffq.fmaster.feature_detail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.usecase.GetArticleUseCase
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.GetRecipeInformationUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipeInformationUseCase: GetRecipeInformationUseCase,
    private val getArticleUseCase: GetArticleUseCase
) : ViewModel() {

    private val type = checkNotNull(savedStateHandle["type"]).toString()
    private val id = checkNotNull(savedStateHandle["id"]).toString().toInt()

    private val _information: MutableStateFlow<Information?> = MutableStateFlow(null)
    val information: StateFlow<Information?> = _information

    private val _article: MutableStateFlow<Details?> = MutableStateFlow(null)
    val article: StateFlow<Details?> = _article

    init {
        typeSelector(type)
    }


    private fun typeSelector(type: String) {
        Log.d("DETAIL", "typeSelector: $type")
        when (type) {
            DetailType.Recipe.name -> {
                Log.d("DETAIL", "typeSelector: loading recipe")
                loadRecipe()
            }

            DetailType.Article.name -> {
                loadArticle()
            }
        }
    }


    private fun loadRecipe() {
        viewModelScope.launch {
            Log.d("DETAIL", "loadRecipe: loading...")
            getRecipeInformationUseCase(
                onSuccess = {
                    Log.d("DETAIL", "loadRecipe: $it")
                    _information.value = it
                },
                onError = {
                    Log.d("DETAIL", "loadRecipe, onError $it")
                },
                id = id
            )
        }
    }

    private fun loadArticle() {

        viewModelScope.launch {
            if (_article.value != null) {
                _article.value = null
            }

            getArticleUseCase(
                onSuccess = {
                    _article.value = it
                },
                onError = {
                    Log.d("DETAIL", "loadArticle: $it")
                },
                id = id
            )
        }

    }
}

sealed class DetailType(val name: String) {
    object Article : DetailType("article")
    object Product : DetailType("rskrf_product")
    object Recipe : DetailType("recipe")
}