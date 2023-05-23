package ru.lffq.fmaster.feature_inventory.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_inventory.data.cart.CartEntity
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryEntity
import ru.lffq.fmaster.feature_inventory.domain.etc.UIEntity


@Composable
fun ItemsFilter() {

}

@Composable
fun ItemsList() {

}

fun LazyListScope.inventoryEntities(entities: List<InventoryEntity>) {
    items(entities) {

    }
}

fun LazyListScope.cartEntities(entities: List<CartEntity>) {
    items(entities) {

    }
}

@Composable
fun EmptyChecker(entities: List<UIEntity>, content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        if (entities.isEmpty()) {
            Text(
                text = stringResource(id = R.string.inventory_nothing_to_show),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            content()
        }
    }
}

@Composable
fun CartCard(entity: CartEntity) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(entity.thumbnail)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Surface {
        Row {
            Image(painter = painter, contentDescription = null, modifier = Modifier.weight(1f))
            Column(Modifier.weight(1.5f)) {
                Text(text = entity.title)
                Text(text = entity.price.toString())
                CountSelector(amount = entity.amount, onIncreaseClick = { /*TODO*/ }) {

                }
            }
        }
    }
}


@Composable
fun InventoryCard(entity: InventoryEntity, onClick: (entity: InventoryEntity) -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(entity.thumbnailUrl)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Surface(
        Modifier
            .padding(16.dp)
            .animateContentSize()
            .clickable { onClick(entity) }) {
        Row {
            Image(painter = painter, contentDescription = "food image")
            Column(Modifier.padding(16.dp)) {
                Text(text = entity.title)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${entity.category}, amount: ${entity.amount}")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopBar() {
    TopAppBar(
        title = { Text(text = "Добавить") }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InventoryTopBar(
    pagerState: PagerState,
    onTabClick: (i: Int) -> Unit,
) {
    val types = listOf(InventoryType.Me, InventoryType.Cart)
    val currentTab = pagerState.currentPage

    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text(stringResource(id = R.string.inventory_title)) },
            actions = {},
            navigationIcon = {}
        )
        TabRow(selectedTabIndex = currentTab) {
            types.forEachIndexed { i, type ->
                Tab(
                    selected = false,
                    onClick = {
                        onTabClick(i)
                    },
                    text = {
                        Text(
                            text = stringResource(type.title),
                            style = MaterialTheme.typography.titleSmall
                        )
                    })
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InventoryFAB(pagerState: PagerState, onAddClick: () -> Unit, onGoCatalogClick: () -> Unit) {
    FloatingActionButton(
        onClick = {
            when (pagerState.currentPage) {
                0 -> onAddClick()
                1 -> onGoCatalogClick()
            }

        },
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
    ) {
        Crossfade(targetState = pagerState.currentPage) {
            when (it) {
                0 -> Icon(Icons.Default.Add, contentDescription = null)
                1 -> Icon(Icons.Default.ShoppingCart, contentDescription = null)
            }
        }
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
    }
}

@Composable
fun CountSelector(amount: Int, onIncreaseClick: () -> Unit, onDecreaseClick: () -> Unit) {
    Row {
        IconButton(onClick = onDecreaseClick, content = {
            Icon(
                painterResource(id = R.drawable.ic_remove_24),
                contentDescription = null
            )

        })
        Text(text = "amount")
        IconButton(onClick = onIncreaseClick, content = {
            Icon(Icons.Default.Add, contentDescription = null)
        })
    }
}
