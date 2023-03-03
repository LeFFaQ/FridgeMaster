package ru.lffq.fmaster.feature_inventory.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ru.lffq.fmaster.feature_inventory.data.InventoryEntity

@Composable
fun Layout(modifier: Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.White),
        content = content
    )
}

@Composable
fun ItemsFilter() {

}

@Composable
fun ItemsList() {

}


@Composable
fun InventoryCard(entity: InventoryEntity, selected: Boolean) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(entity.thumbnailUrl)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Surface(
        Modifier
            .padding(16.dp)
            .animateContentSize()
    ) {
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
fun InventoryTopBar(
    layout: InventoryLayout
) {
    TopAppBar(
        title = {
            Text(layout.title)
        },
        actions = {
            when (layout) {
                is InventoryLayout.DetailsLayout -> Icon(Icons.Default.Delete, contentDescription = "Delete entity")
                else -> Unit
            }
        },
        navigationIcon = {
            when (layout) {
                is InventoryLayout.MainLayout -> Unit
                else -> {
                    if (layout is ClosableLayout) {
                        BackButton(onClick = { layout.close() })
                    }
                }
            }
        }
    )
}

@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        content = {
            Icon(Icons.Default.Add, contentDescription = "Add new")
        }
    )
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
    }
}
