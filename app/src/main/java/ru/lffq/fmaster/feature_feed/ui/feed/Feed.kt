package ru.lffq.fmaster.feature_feed.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.feature_detail.ui.Detail
import ru.lffq.fmaster.feature_detail.ui.DetailType
import ru.lffq.fmaster.ui.components.WidthClass

val PADDING_CONSTANT = Modifier.padding(horizontal = 12.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feed(
    vm: FeedViewModel,
    navController: NavHostController,
    widthClass: WidthClass,
    displayFeatures: List<DisplayFeature>
) {
    val suggestionImageSize = remember(widthClass) {
        if (widthClass is WidthClass.Medium) 196.dp else 128.dp
    }

    val articles by vm.articles.collectAsStateWithLifecycle()
    val sections by vm.sections.collectAsStateWithLifecycle(emptyList())

    val dailyRecipe by vm.randomDailyRecipe.collectAsStateWithLifecycle()
    val networkConnection by vm.networkConnection.collectAsStateWithLifecycle(ConnectionObserver.Status.Unavailable)

    val (hasContent, onContentChange) = remember { mutableStateOf(false) }
    val content by vm.content.collectAsStateWithLifecycle()

    val (query, onQueryChange) = remember {
        mutableStateOf("")
    }

    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFBEBEBE))
        ) {

            if (widthClass is WidthClass.Expanded) {
                FeedTwoPane(
                    recipe = dailyRecipe,
                    onRecipeClick = { vm.onEvent(FeedEvent.OnDailyRecipeClick(it)) },
                    searchValue = query,
                    onSearchValueChange = onQueryChange,
                    onSearch = {},
                    onActiveChange = {},
                    displayFeatures = displayFeatures,
                    paneModifier = Modifier,
                    twoPaneModifier = Modifier,
                    articles = articles,
                    sections = sections,
                    onArticleClick = { vm.onEvent(FeedEvent.OnArticleClick(it)) },
                    hasContent = hasContent,
                    content = {
                        when (content) {
                            is FeedContent.NoContent -> {}
                            is FeedContent.RecipeInformation -> {
                                Detail(
                                    information = (content as FeedContent.RecipeInformation).information,
                                    type = DetailType.Recipe,
                                    onBackClick = { vm.onEvent(FeedEvent.ClearContent) }
                                )
                            }

                            is FeedContent.ArticleDetails -> {
                                Detail(
                                    details = (content as FeedContent.ArticleDetails).details,
                                    type = DetailType.Article,
                                    onBackClick = { vm.onEvent(FeedEvent.ClearContent) }
                                )
                            }
                        }
                    },
                    suggestionImageSize = suggestionImageSize
                )
            } else {
                Box(Modifier.fillMaxSize()) {
                    FeedOnePane(
                        recipe = dailyRecipe,
                        onRecipeClick = { vm.onEvent(FeedEvent.OnDailyRecipeClick(it)) },
                        searchValue = query,
                        onSearchValueChange = onQueryChange,
                        onSearch = {},
                        onActiveChange = {},
                        articles = articles,
                        sections = sections,
                        onArticleClick = { vm.onEvent(FeedEvent.OnArticleClick(it)) },
                        modifier = if (widthClass == WidthClass.Compact) Modifier.padding(12.dp) else Modifier,
                        suggestionImageSize = suggestionImageSize
                    )
                    when (content) {
                        is FeedContent.NoContent -> {}
                        is FeedContent.RecipeInformation -> {
                            Detail(
                                information = (content as FeedContent.RecipeInformation).information,
                                type = DetailType.Recipe,
                                onBackClick = { vm.onEvent(FeedEvent.ClearContent) }
                            )
                        }

                        is FeedContent.ArticleDetails -> {
                            Detail(
                                details = (content as FeedContent.ArticleDetails).details,
                                type = DetailType.Article,
                                onBackClick = { vm.onEvent(FeedEvent.ClearContent) }
                            )
                        }
                    }

                }
            }
//            Box(Modifier.fillMaxSize()) {
//
//                LazyColumn(
//                    modifier = Modifier.wrapContentHeight()
//                ) {
//                    item { addHelloMessage(vm.helloMessage, modifier = PADDING_CONSTANT) }
//                    item { Spacer(modifier = Modifier.height(16.dp)) }
//
//                    dailyRecipe?.let {
//                        item {
//                            addSuggestion(dailyRecipe!!, PADDING_CONSTANT, onClick = {
//                                navController.navigate("detail/recipe/${dailyRecipe!!.id}")
//                            })
//                        }
//                    }
//                    item { Spacer(modifier = Modifier.height(16.dp)) }
//                    item {
//                        val (string, setter) = remember { mutableStateOf("") }
//
//                        DockedSearchBar(
//                        query = string,
//                        onQueryChange = setter,
//                        onSearch = {},
//                        active = string.isNotEmpty(),
//                        onActiveChange = {}
//                    ) {
//
//                    } }
//
//                    item { addNewsHeader(sections, modifier = PADDING_CONSTANT) }
//                    item { Divider() }
//
//                    addNews(articles = articles, onArticleClicked = {
//                        navController.navigate("detail/article/${it}")
//                    }, showMoreClicked = {})
//                }
//
//            }

        }
    }
}
