package ru.lffq.fmaster.feature_inventory.data

import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.feature_inventory.domain.IInventoryRepository

class InventoryRepository(private val dao: InventoryDao) : IInventoryRepository {

    override fun getInventory(): Flow<List<InventoryEntity>> {
        return dao.getInventory()
    }

    override suspend fun getInventoryEntityById(id: Int): InventoryEntity? {
        return dao.getInventoryEntityById(id)
    }

    override suspend fun insertEntity(entity: InventoryEntity) {
        dao.insertEntity(entity)
    }

    override suspend fun deleteEntity(entity: InventoryEntity) {
        dao.deleteEntity(entity)
    }
}
