package ru.lffq.fmaster.feature_detail.ui

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.ireward.htmlcompose.HtmlText
import ru.lffq.fmaster.R
import ru.lffq.fmaster.common.Constant
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Product
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information

typealias DetailResource = Resource<Details>
typealias InformationResource = Resource<Information>

@Composable
fun Detail(details: DetailResource, type: DetailType.Article, onBackClick: () -> Unit) {
    val data = details.data
    BackHandler(onBack = onBackClick)

    Surface {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                DetailImage(imageUrl = data?.thumbnail)
            }
            item {
                DetailTitle(
                    title = data?.title,
                    provider = "Роскачество",
                    providerUrl = data?.articleLink
                )
            }
        }
    }
}

@Composable
fun Detail(product: Product) {


}

@Composable
fun Detail(information: InformationResource, type: DetailType.Recipe, onBackClick: () -> Unit) {
    val data = information.data
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    BackHandler(onBack = onBackClick)


    Surface {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                DetailImage(imageUrl = data?.image)
            }
            item {
                DetailTitle(
                    title = data?.title,
                    provider = data?.sourceName,
                    providerUrl = data?.sourceUrl
                )
            }
            item {
                Text(
                    text = "Описание",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(4.dp)
                        .placeholder(
                            visible = data?.summary == null,
                            shape = CardDefaults.shape,
                            color = MaterialTheme.colorScheme.secondary,
                            highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.secondaryContainer)
                        )
                )
                RecipeHtmlText(text = data?.summary)
            }
            item {
                Text(
                    text = "WIP",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun DetailImage(imageUrl: String?) {
    val (imageLoaded, onImageLoaded) = remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        onSuccess = {
            if (imageUrl != null) {
                onImageLoaded(true)
            }
        },
        model = imageUrl ?: ""
    )
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(192.dp)
            .fillMaxWidth()
            .placeholder(
                visible = (imageUrl == null) && !imageLoaded,
                color = MaterialTheme.colorScheme.secondary,
                highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.secondaryContainer)
            )
    )
}

@Composable
fun DetailTitle(
    title: String?,
    provider: String?,
    providerUrl: String?
) {

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    val (hasValues, onValuesChange) = remember { mutableStateOf(title != null) }

    Log.d(
        "DETAILTITLE",
        "DetailTitle: has values = $hasValues, title = $title, providerUrl = $providerUrl"
    )


    LaunchedEffect(key1 = title) {
        if (title != null) {
            Log.d(
                "LAUNCHEDEFFECT",
                "DetailTitle: has values = $hasValues, title = $title, providerUrl = $providerUrl"
            )
            onValuesChange(true)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(
            text = title ?: "very long title placeholder when loading",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = !hasValues,
                    shape = CardDefaults.shape,
                    color = MaterialTheme.colorScheme.secondary,
                    highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.secondaryContainer)
                )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = provider ?: "long provider placeholder", modifier = Modifier
                    .weight(1f)
                    .placeholder(
                        visible = !hasValues,
                        shape = CardDefaults.shape,
                        color = MaterialTheme.colorScheme.secondary,
                        highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.secondaryContainer)
                    )
            )

            Row(
                Modifier.weight(0.5f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        uriHandler.openUri(
                            providerUrl ?: "https://www.innersloth.com/games/among-us/"
                        )
                    },
                    enabled = hasValues,
                    content = {
                        Icon(
                            painterResource(id = R.drawable.ic_open_in_new_24),
                            null
                        )
                    })
                IconButton(
                    enabled = hasValues,
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getString(R.string.not_working),
                            Toast.LENGTH_SHORT
                        ).show()
                    }, content = { Icon(Icons.Default.Share, null) })
            }

        }
        Divider()
    }
}

@Composable
fun RecipeHtmlText(text: String?) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    HtmlText(
        text = text ?: Constant.LOREM,
        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .placeholder(
                visible = text == null,
                shape = CardDefaults.shape,
                color = MaterialTheme.colorScheme.secondary,
                highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.secondaryContainer)
            ),
        linkClicked = {
            Toast.makeText(
                context,
                " Ссылки недействительны. Проблема либо с Html-форматором, либо с API",
                Toast.LENGTH_LONG
            ).show()
            uriHandler.openUri(it)
        },
        URLSpanStyle = SpanStyle(color = MaterialTheme.colorScheme.primary)
    )
}