package ru.lffq.fmaster.feature_inventory.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.common.Resource
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryEntity
import ru.lffq.fmaster.feature_inventory.domain.IInventoryRepository

class InventoryUseCases(
    private val repository: IInventoryRepository
) {

    fun getInventory(): Flow<List<InventoryEntity>> {
        return repository.getInventory()
    }

    suspend fun getInventoryEntityById(id: Int): Resource<InventoryEntity> {
        val result = repository.getInventoryEntityById(id)
            ?: return Resource.Error("Something went wrong")

        return Resource.Success(result)
    }

    suspend fun insertEntity(entity: InventoryEntity) {
        repository.insertEntity(entity)
    }

    suspend fun deleteEntity(entity: InventoryEntity) {
        repository.deleteEntity(entity)
    }

}
