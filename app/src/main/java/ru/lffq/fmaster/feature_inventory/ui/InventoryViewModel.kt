package ru.lffq.fmaster.feature_inventory.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.lffq.fmaster.feature_inventory.data.InventoryEntity
import ru.lffq.fmaster.feature_inventory.domain.InventoryUseCases
import javax.inject.Inject


@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val useCase: InventoryUseCases
) : ViewModel() {
    //Todo: Migrate to state dataclass

    private val _entities: MutableStateFlow<List<InventoryEntity>> = MutableStateFlow(emptyList())
    val entities = _entities.asStateFlow()

    private val _layout: MutableState<InventoryLayout> = mutableStateOf(InventoryLayout.MainLayout)
    val currentLayout: State<InventoryLayout> = _layout

    init {
        getInventoryEntities()
    }

    private fun getInventoryEntities() {
        viewModelScope.launch {
            useCase.getInventory().collect {
                _entities.value = it
            }
        }
    }

    fun onEvent(event: InventoryEvent) {
        when (event) {
            is InventoryEvent.OnAddButtonClicked -> {
                _layout.value = InventoryLayout.AddLayout(
                    onCloseLayout = { onEvent(InventoryEvent.OnCloseButtonClicked) }
                )
            }

            is InventoryEvent.OnEntityAdded -> {
                viewModelScope.launch {
                    useCase.insertEntity(event.entity)
                    onEvent(InventoryEvent.OnCloseButtonClicked)
                }
            }

            is InventoryEvent.OnEntityDetailsShow -> {
                _layout.value = InventoryLayout.DetailsLayout(
                    entity = event.entity,
                    onCloseLayout = { onEvent(InventoryEvent.OnCloseButtonClicked) },
                    onDeleteEntity = { onEvent(InventoryEvent.OnEntityDeleteClick(event.entity)) },
                )
            }
            is InventoryEvent.OnEntityDeleteClick -> {
                viewModelScope.launch {
                    useCase.deleteEntity(event.entity)
                }
                onEvent(InventoryEvent.OnCloseButtonClicked)
            }
            InventoryEvent.OnCloseButtonClicked -> {
                _layout.value = InventoryLayout.MainLayout
            }
        }
    }
}

sealed class InventoryEvent {
    object OnAddButtonClicked : InventoryEvent()
    object OnCloseButtonClicked : InventoryEvent()
    data class OnEntityAdded(val entity: InventoryEntity) : InventoryEvent()
    data class OnEntityDetailsShow(val entity: InventoryEntity) : InventoryEvent()
    data class OnEntityDeleteClick(val entity: InventoryEntity) : InventoryEvent()
}

sealed class InventoryLayout(val title: String) {

    object MainLayout : InventoryLayout("Inventory")

    data class DetailsLayout(
        val entity: InventoryEntity,
        val onCloseLayout: () -> Unit,
        val onDeleteEntity: () -> Unit
    ) : InventoryLayout("Details"), ClosableLayout {
        override fun close() = onCloseLayout()
    }

    data class AddLayout(val onCloseLayout: () -> Unit) : InventoryLayout("Add"), ClosableLayout {
        override fun close() = onCloseLayout()
    }
}

interface ClosableLayout {
    fun close()
}
