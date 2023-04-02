package ru.lffq.fmaster.feature_feed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Article

@Composable
fun ArticleCard(article: Article) {

    var isLoading by rememberSaveable {
        mutableStateOf(true)
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .placeholder(
                visible = isLoading,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(0.7f),
                highlight = PlaceholderHighlight.fade(highlightColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ),
    ) {
        Box {
            AsyncImage(
                //колхоз от роскачества
                model = article.thumbnail.replace("255_320_240", "456_270_240"),
                contentDescription = " Article image",
                modifier = Modifier
                    .height(256.dp),
                onSuccess = { isLoading = false },
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(5.dp)
                .fillMaxWidth()
            //.align(Alignment.BottomCenter)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
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
        )
    )

}