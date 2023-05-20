package ru.lffq.fmaster.feature_inventory.domain


import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryEntity

interface IInventoryRepository {

    fun getInventory(): Flow<List<InventoryEntity>>

    suspend fun getInventoryEntityById(id: Int): InventoryEntity?

    suspend fun insertEntity(entity: InventoryEntity)

    suspend fun deleteEntity(entity: InventoryEntity)

}
