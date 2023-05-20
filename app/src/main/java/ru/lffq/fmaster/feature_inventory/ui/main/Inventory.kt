package ru.lffq.fmaster.feature_inventory.ui

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_fakedata.Food
import ru.lffq.fmaster.feature_fakedata.FoodInFridge
import ru.lffq.fmaster.feature_inventory.data.cart.CartEntity
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryEntity
import ru.lffq.fmaster.ui.components.WidthClass


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Inventory(
    vm: InventoryViewModel,
    pagerState: PagerState,
    widthClass: WidthClass,
) {
    val layout by vm.layout

    val inventoryEntities: List<InventoryEntity> by vm.inventoryEntities.collectAsState()
    val cartEntities: List<CartEntity> by vm.cartEntities.collectAsState()


    when (layout) {
        is InventoryLayout.Main -> {
            MainLayout(
                pagerState,
                inventoryEntities,
                cartEntities,
                modifier = Modifier.fillMaxSize(),
                widthClass = widthClass
            )
        }

        is InventoryLayout.Add -> {

        }

        else -> {}
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainLayout(
    pagerState: PagerState,
    inventory: List<InventoryEntity>,
    cart: List<CartEntity>,
    widthClass: WidthClass,
    modifier: Modifier
) {

    HorizontalPager(
        state = pagerState,
        pageCount = 2
    ) {
        val groups = remember {
            Food.FoodType.values()
        }
        val spamCount = remember(widthClass) {
            when (widthClass) {
                is WidthClass.Compact -> 2
                is WidthClass.Medium -> 3
                is WidthClass.Expanded -> 5
            }
        }

        // FIXME: refactor this shit
        if (it == 0) {
            //EmptyChecker(entities = inventory) {


            LazyVerticalGrid(
                columns = GridCells.Fixed(spamCount),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(all = 8.dp)
            ) {
                groups.forEach { group ->
                    if (FoodInFridge.foods.any { food -> food.type == group }) {
                        item(span = {
                            GridItemSpan(spamCount)
                        }) {
                            Text(
                                text = stringResource(group.title),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                        items(FoodInFridge.foods.filter { food -> food.type == group }) {
                            ProductCard(product = it)
                        }
                    }
                }
            }

            //}
        } else if (it == 1) {
            EmptyChecker(entities = cart) {
                LazyColumn() {
                    cartEntities(cart)
                }
            }
        }
    }
}


@Composable
fun ProductCard(product: Food) {
    val context = LocalContext.current

    val painter = rememberAsyncImagePainter(model = product.image)


    ElevatedCard(Modifier.fillMaxWidth()) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(124.dp)
                .clip(CardDefaults.elevatedShape)
        )
        Column(
            Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    12.dp,
                    alignment = Alignment.CenterHorizontally
                )


            ) {
                FilledTonalIconButton(
                    modifier = Modifier.size(36.dp),
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getText(R.string.not_working),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    content = { Icon(Icons.Default.Add, contentDescription = null) })

                Text(text = "${product.amount} ${product.amountMeasure}")

                FilledTonalIconButton(
                    modifier = Modifier.size(36.dp),
                    onClick = {
                        Toast.makeText(
                            context,
                            context.getText(R.string.not_working),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    content = { Icon(painterResource(id = R.drawable.ic_remove_24), null) })
            }
        }


    }
}
