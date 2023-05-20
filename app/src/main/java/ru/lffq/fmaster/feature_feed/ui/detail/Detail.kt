package ru.lffq.fmaster.feature_detail.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_rskrf.domain.model.news.Details
import ru.lffq.fmaster.feature_rskrf.domain.model.products.Product
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Information
import ru.lffq.fmaster.ui.theme.FridgeMasterTheme


@Composable
fun Detail(details: Details) {
    DetailContent(
        title = details.title,
        provider = "Роскачество",
        providerUrl = details.articleLink,
        bannerPainter = rememberAsyncImagePainter(
            model = details.thumbnail
        )
    ) {
        Text(text = details.description)
    }
}

@Composable
fun Detail(product: Product) {


}

@Composable
fun Detail(type: DetailType.Recipe, value: Information) {
    DetailContent(
        title = value.title!!,
        provider = value.sourceName!!,
        providerUrl = value.sourceUrl!!,
        bannerPainter = rememberAsyncImagePainter(
            model = value.image!!
        )
    ) {
        Text("Ингредиенты")
        value.extendedIngredients?.forEach { ingredient ->
            Text(text = ingredient?.name!!)
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    titleTextStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    provider: String,
    providerUrl: String,
    chips: (LazyListScope.() -> Unit)? = null,
    bannerPainter: Painter? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val context = LocalContext.current
    val localUriHandler = LocalUriHandler.current

    val state = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize()) {

        LazyColumn() {
            item {
                Column() {
                    bannerPainter?.let {
                        Image(
                            painter = bannerPainter,
                            contentDescription = null,
                            modifier = Modifier
                                .height(192.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        chips?.let { LazyRow() { it() } }

                        Text(
                            text = title,
                            style = titleTextStyle,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = provider)

                            Row(
                                Modifier.weight(0.5f),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = {
                                        localUriHandler.openUri(providerUrl)
                                    },
                                    content = {
                                        Icon(
                                            painterResource(id = R.drawable.ic_open_in_new_24),
                                            null
                                        )
                                    })
                                IconButton(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            context.getString(R.string.not_working),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }, content = { Icon(Icons.Default.Share, null) })
                            }

                        }
                        Divider(Modifier.padding(vertical = 8.dp))


                        this.content()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DCPreview() {
    FridgeMasterTheme {
        DetailContent(
            title = "very very very very very fucking long article/food/recipe title",
            bannerPainter = rememberAsyncImagePainter(model = "https://w7.pngwing.com/pngs/241/986/png-transparent-amogus.png"),
            provider = "роскачество например",
            providerUrl = "https://ru.wikipedia.org/wiki/Cock_and_ball_torture"
        ) {

        }
    }
}