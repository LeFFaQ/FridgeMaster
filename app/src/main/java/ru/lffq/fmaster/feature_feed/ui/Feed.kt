package ru.lffq.fmaster.feature_feed.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Feed(vm: FeedViewModel) {

    val layout by vm.currentLayout
    val articles by vm.articles.collectAsState()

    Log.d("Feed", "Feed ui: recompose")
    Scaffold(
        topBar = {
//            Surface(modifier = Modifier.height(152.dp), shadowElevation = 10.dp) {
//                Column {
//                    Text(text = "TITLE/Title")
//                    TabRow(selectedTabIndex = 0) {
//                        listOf(1, 2, 3, 4).forEach {
//                            Tab(selected = false, onClick = { /*TODO*/ }) {
//                                Text(text = it.toString())
//                            }
//                        }
//                    }
//                }
//            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (layout) {
                is FeedLayout.MainLayout -> {
                    MainLayout(articles)
                }
                is FeedLayout.ArticleLayout -> {
                    ArticleLayout()
                }
            }
        }
    }
}

@Composable
fun MainLayout(
    list: List<Article>,
    onItemClicked: ((item: Article) -> Unit)? = null
) {
    if (list.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn() {
            items(list) { article ->
                ArticleCard(article)
                //Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

@Composable
fun ArticleLayout(id: Int? = null) {

}
