package ru.lffq.fmaster.feature_inventory.data.inventory

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [InventoryEntity::class],
    version = 1
)
abstract class InventoryDB : RoomDatabase() {

    abstract val inventoryDao: InventoryDao

    companion object {
        const val DATABASE_NAME = "products"
    }

}
