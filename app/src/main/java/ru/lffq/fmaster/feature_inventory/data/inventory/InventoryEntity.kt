package ru.lffq.fmaster.feature_inventory.data.inventory

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.lffq.fmaster.feature_inventory.domain.etc.UIEntity

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
) : UIEntity
