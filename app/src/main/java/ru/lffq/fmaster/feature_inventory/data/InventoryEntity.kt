package ru.lffq.fmaster.feature_inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val category: String,
    val manufacturer: String,
    val amount: String,
    val expiresAt: Long
)
