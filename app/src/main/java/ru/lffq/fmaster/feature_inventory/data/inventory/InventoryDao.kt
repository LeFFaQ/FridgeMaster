package ru.lffq.fmaster.feature_inventory.data.inventory

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Query("SELECT * FROM inventory")
    fun getInventory(): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE id = :id")
    suspend fun getInventoryEntityById(id: Int): InventoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntity(entity: InventoryEntity)

    @Delete
    suspend fun deleteEntity(entity: InventoryEntity)


}
