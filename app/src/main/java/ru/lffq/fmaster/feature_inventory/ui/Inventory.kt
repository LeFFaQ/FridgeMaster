package ru.lffq.fmaster.feature_inventory.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ru.lffq.fmaster.feature_inventory.data.InventoryEntity
import ru.lffq.fmaster.ui.components.VSpacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inventory(vm: InventoryViewModel = hiltViewModel()) {

    val entities: List<InventoryEntity> by vm.entities.collectAsState()
    val layout by vm.currentLayout

    Scaffold(
        topBar = {
            InventoryTopBar(layout)
        },
        floatingActionButton = {
            when (layout) {
                is InventoryLayout.MainLayout -> FAB {
                    Log.d("UI", "Inventory: Fab clicked. Current layout: $layout")
                    vm.onEvent(InventoryEvent.OnAddButtonClicked)
                }
                else -> Unit
            }
        }
    ) {
        Box(Modifier.padding(it)) {

            Log.d("UI", "Inventory: Fab clicked. Current layout: $layout")


            when (layout) {
                is InventoryLayout.AddLayout -> {
                    AddLayout { entity ->
                        vm.onEvent(InventoryEvent.OnEntityAdded(entity))
                    }
                }
                is InventoryLayout.DetailsLayout -> {
                    DetailsLayout(entity = (layout as InventoryLayout.DetailsLayout).entity)
                }
                else -> MainLayout(entities = entities) { entity ->
                    vm.onEvent(InventoryEvent.OnEntityDetailsShow(entity))
                }
            }
        }
    }
}

@Composable
fun MainLayout(
    entities: List<InventoryEntity>,
    onEntityClicked: (entity: InventoryEntity) -> Unit
) {
    if (entities.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(entities) { entity ->
                InventoryCard(entity = entity, onClick = { onEntityClicked(entity) })
            }
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "There's nothing here yet.")
            //TODO: Replace hardcode string
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLayout(onAddClick: (entity: InventoryEntity) -> Unit) {

    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Layout(modifier = Modifier.padding(16.dp)) {
        Column() {
            TextField(value = title, onValueChange = { title = it })
            TextField(value = category, onValueChange = { category = it })
            TextField(value = manufacturer, onValueChange = { manufacturer = it })
            TextField(value = amount, onValueChange = { amount = it })
            Button(onClick = {
                onAddClick(
                    InventoryEntity(
                        title = title,
                        imageUrl = "",
                        thumbnailUrl = "",
                        category = category,
                        manufacturer = manufacturer,
                        amount = amount,
                        expiresAt = 0
                    )
                )
            }
            ) {
                Text(text = "Add")
            }
        }

    }
}

@Composable
fun DetailsLayout(
    entity: InventoryEntity,
) {
    Layout(modifier = Modifier) {

        Log.d("Entity", "DetailsLayout: $entity")
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entity.imageUrl)
                .size(Size.ORIGINAL) // Set the target size to load the image at.
                .build()
        )

        Column {
            //Image(painter = painter, contentDescription = "Image")
            VSpacer(12)
            Text(text = entity.title)
            VSpacer(2)
            Text(text = entity.category)
            VSpacer(8)
            Text(text = entity.manufacturer)
            VSpacer(8)
            Text(text = "Amount: ${entity.amount}")
            VSpacer(16)
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }
}

@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout(
        entities = arrayListOf(
            InventoryEntity(
                0,
                "Обед",
                "https://chpic.su/_data/stickers/o/obed_yutnenko/obed_yutnenko_003.webp",
                "https://chpic.su/_data/stickers/o/obed_yutnenko/obed_yutnenko_003.webp",
                "78",
                "вконтакте",
                "100g",
                0
            ),
            InventoryEntity(
                0,
                "Очки усы",
                "https://chpic.su/_data/stickers/o/obed_yutnenko/obed_yutnenko_003.webp",
                "https://chpic.su/_data/stickers/o/obed_yutnenko/obed_yutnenko_003.webp",
                "78",
                "не Zнаю",
                "100g",
                0
            )
        )
    ) {}
}
