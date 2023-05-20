package ru.lffq.fmaster.feature_feed.ui.feed

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import coil.compose.AsyncImage
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import ru.lffq.fmaster.R
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_detail.ui.provideDetailChips
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random

@Composable
fun FeedTwoPane(
    recipe: Resource<Random.Recipe>,
    articles: List<Article>,
    sections: List<Article.Section>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    displayFeatures: List<DisplayFeature>,
    modifier: Modifier
) {
    val strategy = remember { HorizontalTwoPaneStrategy(0.45f, 8.dp) }

    TwoPane(
        first = {
            FeedOnePane(
                recipe = recipe,
                articles = articles,
                sections = sections,
                searchValue = searchValue,
                onSearchValueChange = onSearchValueChange,
                onSearch = onSearch,
                onActiveChange = onActiveChange,
            )
        },
        second = { /*TODO*/ },
        strategy = strategy,
        displayFeatures = displayFeatures,
        modifier = modifier.padding(24.dp)
    )
}

@Composable
fun FeedOnePane(
    recipe: Resource<Random.Recipe>,
    articles: List<Article>,
    sections: List<Article.Section>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit
) {
    val listState = rememberLazyListState()


    Box {
        FeedSearchBar(
            query = searchValue,
            onQueryChange = onSearchValueChange,
            onSearch = onSearch,
            onActiveChange = onActiveChange,
            listState = listState,
            modifier = PADDING_CONSTANT
                .fillMaxWidth()
                .padding(top = 8.dp)
                .align(Alignment.TopCenter)
        ) {

        }

        LazyColumn(
            state = listState
        ) {
            item {
                addSuggestion(
                    recipe = recipe,
                    modifier = PADDING_CONSTANT.padding(top = 72.dp),
                    onClick = { })
            }
            item {
                addNewsHeader(sections = sections, modifier = PADDING_CONSTANT)
            }
            addNews(articles = articles, {}, {})
        }
    }
    Box {


    }
}

@Composable
fun ChipsRow(list: List<Article.Section>, onChipClicked: (() -> Unit)? = null) {

    LazyRow() {
        //val chipsTitles = list.map { it.section }.distinctBy { it.title }

        items(list) {
            AssistChip(onClick = { }, label = { Text(text = it.title) })
            if (list.last() != it || list.first() != it) {
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onActiveChange: (Boolean) -> Unit,
    listState: LazyListState,
    modifier: Modifier,

    content: @Composable ColumnScope.() -> Unit
) {
//    val elevation by animateDpAsState(
//        targetValue = if (listState.canScrollForward) 6.dp else 0.dp
//    )
    val elevation = remember {
        6.dp
    }

    DockedSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        active = query.length > 1,
        onActiveChange = onActiveChange,
        leadingIcon = {
            if (query.length > 1) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            } else Icon(Icons.Default.Search, contentDescription = null)

        },
        modifier = modifier
            .shadow(elevation, SearchBarDefaults.dockedShape)
    ) {

    }
}

@Composable
fun ArticleCard(article: Article, onArticleClicked: (id: Int) -> Unit) {

    var isLoading by rememberSaveable {
        mutableStateOf(true)
    }

    remember {
        MutableInteractionSource()
    }

    Column(
        modifier = Modifier
            .clickable(onClick = { onArticleClicked(article.id) })
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        AsyncImage(
            //колхоз от роскачества
            model = article.thumbnail.replace("255_320_240", "456_270_240"),
            contentDescription = " Article image",
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .clip(RoundedCornerShape(8.dp)),
            onSuccess = { isLoading = false },
            contentScale = ContentScale.FillHeight,
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Роскачество",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Text(
                text = article.date,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun FakeArticleCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(256.dp)
                .fillMaxWidth()
                .placeholder(
                    true,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(8.dp),
                    highlight = PlaceholderHighlight.fade(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.2f
                        )
                    )
                )
        )
        Text(
            text = stringResource(id = R.string.feed_article_card_title_placeholder),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .placeholder(
                    true,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(8.dp),
                    highlight = PlaceholderHighlight.fade(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.2f
                        )
                    )
                )
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.feed_article_card_author_placeholder),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.placeholder(
                    true,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp),
                    highlight = PlaceholderHighlight.fade(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.1f
                        )
                    )
                )
            )
            Text(
                text = stringResource(R.string.feed_article_card_date_placeholder),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.placeholder(
                    true,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(8.dp),
                    highlight = PlaceholderHighlight.fade(
                        MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.2f
                        )
                    )
                )
            )
        }
    }
}


@SuppressLint("ComposableNaming")
@Composable
fun LazyItemScope.addHelloMessage(message: String, modifier: Modifier) {
    Column(modifier) {
        Text(text = "Привет!", fontWeight = FontWeight.Bold)
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
    }


}


@SuppressLint("ComposableNaming")
@Composable
fun LazyItemScope.addBanner(canBeShown: Boolean, modifier: Modifier) {

    if (canBeShown) {
        Card(modifier = modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "Это рекламный баннер",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Ничего умнее я не придумал. Шанс рандомный (Random.nextBoolean)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("ComposableNaming")
@Composable
fun addSuggestion(recipe: Resource<Random.Recipe>, modifier: Modifier, onClick: () -> Unit) {

    val chips = remember(recipe) {

        if (recipe is Resource.Success) {
            provideDetailChips(
                healthy = recipe.data?.veryHealthy!!,
                popular = recipe.data.veryPopular!!,
                vegetarian = recipe.data.vegetarian!!,
                glutenFree = recipe.data.glutenFree!!,
                dairyFree = recipe.data.dairyFree!!,
                cheap = recipe.data.cheap!!
            )
        } else {
            null
        }
    }


    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            Modifier
                .fillMaxSize()
        ) {

            AsyncImage(
                model = recipe.data?.image,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(124.dp)
                    .clip(CardDefaults.shape)
                    .placeholder(
                        visible = recipe is Resource.Loading,
                        shape = CardDefaults.shape,
                        color = MaterialTheme.colorScheme.secondary
                    )

            )
            Column(Modifier.padding(8.dp)) {
                recipe.data?.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                LazyRow {
                    chips?.forEach { (detail, boolean) ->
                        if (boolean) {
                            item {
                                SuggestionChip(title = detail.title, icon = detail.icon)
                                if (detail != chips.keys.last()) {
                                    Spacer(Modifier.width(4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SuggestionChip(@StringRes title: Int, @DrawableRes icon: Int) {
    ElevatedAssistChip(
        onClick = { /*TODO*/ },
        label = {
            Text(
                text = stringResource(
                    id = title
                )
            )
        },
        leadingIcon = {
            Icon(
                painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint = Color.Unspecified
            )
        }
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun addNewsHeader(sections: List<Article.Section>, modifier: Modifier) {

    Text(text = "Новости", fontWeight = FontWeight.Bold, modifier = modifier.fillMaxWidth())
    if (sections.isNotEmpty()) {
        Column(modifier) {
            ChipsRow(list = sections)
        }
    } else {
        Row(modifier) {
            (1..4).forEach {
                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            text = "............", modifier = Modifier.placeholder(
                                true,
                                MaterialTheme.colorScheme.onSurface.copy(0.3f),
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderHighlight.fade(
                                    MaterialTheme.colorScheme.onSurface.copy(0.1f)
                                )
                            )
                        )
                    },
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

fun LazyListScope.addNews(
    articles: List<Article>,
    onArticleClicked: (id: Int) -> Unit,
    showMoreClicked: () -> Unit
) {
    if (articles.isNotEmpty()) {
        items(articles.slice(0..3)) {
            ArticleCard(it, onArticleClicked)
            Divider()

        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                TextButton(onClick = showMoreClicked, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Показать больше...")
                }
            }
        }
    } else {
        item {
            FakeArticleCard()
        }
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    ArticleCard(
        article = Article(
            date = "May 10th",
            description = "В 22:28 по МСК над Саратовым появилась розовая дыра, из которой вылез огромный Амогус. Дети в ахуе",
            id = 1,
            section = Article.Section(1, "АПОКАЛИПСИС"),
            thumbnail = "https://media.sketchfab.com/models/f6ddbea69b364ea19ef9986894004fc0/thumbnails/adbc9230ab9f49c3abf55bdef1c1d25f/2b590f8f02794abbbfabd6d484dfa9d5.jpeg",
            title = "Большой амогус захватил Россию"
        ),
        onArticleClicked = { }
    )

}
