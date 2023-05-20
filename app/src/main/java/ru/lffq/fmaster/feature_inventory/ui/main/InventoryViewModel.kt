package ru.lffq.fmaster.feature_inventory.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.lffq.fmaster.R
import ru.lffq.fmaster.feature_inventory.data.cart.CartEntity
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryEntity
import ru.lffq.fmaster.feature_inventory.domain.usecase.InventoryUseCases
import javax.inject.Inject


@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val useCase: InventoryUseCases
) : ViewModel() {

    private val _layout: MutableState<InventoryLayout> = mutableStateOf(InventoryLayout.Main)
    val layout: State<InventoryLayout> = _layout

    private val _inventoryEntities: MutableStateFlow<List<InventoryEntity>> =
        MutableStateFlow(emptyList())
    val inventoryEntities = _inventoryEntities.asStateFlow()

    private val _cartEntities: MutableStateFlow<List<CartEntity>> = MutableStateFlow(emptyList())
    val cartEntities = _cartEntities.asStateFlow()

    init {
        getInventoryEntities()
    }

    private fun getInventoryEntities() {
        viewModelScope.launch {
            useCase.getInventory().collect {
                _inventoryEntities.value = it
            }
        }
    }

//    fun onEvent(event: InventoryEvent) {
//        when (event) {
////            is InventoryEvent.OnAddButtonClicked -> {
////                _layout.value = InventoryLayout.AddLayout(
////                    onCloseLayout = { onEvent(InventoryEvent.OnCloseButtonClicked) }
////                )
////            }
//
//            is InventoryEvent.OnEntityAdded -> {
//                viewModelScope.launch {
//                    useCase.insertEntity(event.entity)
//                    onEvent(InventoryEvent.OnCloseButtonClicked)
//                }
//            }
//
////            is InventoryEvent.OnEntityDetailsShow -> {
////                _layout.value = InventoryLayout.DetailsLayout(
////                    entity = event.entity,
////                    onCloseLayout = { onEvent(InventoryEvent.OnCloseButtonClicked) },
////                    onDeleteEntity = { onEvent(InventoryEvent.OnEntityDeleteClick(event.entity)) },
////                )
////            }
//            is InventoryEvent.OnEntityDeleteClick -> {
//                viewModelScope.launch {
//                    useCase.deleteEntity(event.entity)
//                }
//                onEvent(InventoryEvent.OnCloseButtonClicked)
//            }
////            inventoryevent.onclosebuttonclicked -> {
////                _layout.value = inventorylayout.mainlayout
////            }
//
//            else -> {}
//        }
//    }
}

sealed class InventoryEvent {


    object OnAddButtonClicked : InventoryEvent()
    object OnCloseButtonClicked : InventoryEvent()
    data class OnEntityAdded(val entity: InventoryEntity) : InventoryEvent()
    data class OnEntityDetailsShow(val entity: InventoryEntity) : InventoryEvent()
    data class OnEntityDeleteClick(val entity: InventoryEntity) : InventoryEvent()
}

sealed class InventoryLayout(val title: String, val destination: String) {

    object Main : InventoryLayout("Inventory", "main")
    object Add : InventoryLayout("Add new one", "add")
}

sealed class InventoryType(@StringRes val title: Int) {
    class Me() : InventoryType(R.string.inventory_title_MY)
    class Cart() : InventoryType(R.string.inventory_title_CART)

}