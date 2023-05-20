package ru.lffq.fmaster.feature_feed.ui.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature
import ru.lffq.fmaster.common.connection.ConnectionObserver
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

    val articles by vm.articles.collectAsState()
    val sections by vm.sections.collectAsState(emptyList())

    val dailyRecipe by vm.randomDailyRecipe.collectAsState()
    val networkConnection by vm.networkConnection.collectAsState(ConnectionObserver.Status.Unavailable)

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
                    searchValue = query,
                    onSearchValueChange = onQueryChange,
                    onSearch = {},
                    onActiveChange = {},
                    displayFeatures = displayFeatures,
                    modifier = Modifier,
                    articles = articles,
                    sections = sections
                )
            } else {
                FeedOnePane(
                    recipe = dailyRecipe,
                    searchValue = query,
                    onSearchValueChange = onQueryChange,
                    onSearch = {},
                    onActiveChange = {},
                    articles = articles,
                    sections = sections
                )

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
