package ru.lffq.fmaster.feature_inventory.data.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.lffq.fmaster.feature_inventory.domain.etc.UIEntity

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey
    val id: Int? = null,
    val rskrfId: Int,
    val title: String,
    val thumbnail: String,
    val price: Double,
    val priceMeasure: String,
    val amount: Int,
    val measure: String,
    val category: Int,
) : UIEntity

