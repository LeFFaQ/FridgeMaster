package ru.lffq.fmaster.feature_inventory.data.cart

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart WHERE id = :id")
    suspend fun getFromCartById(id: Int): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToCart(entity: CartEntity)

    @Delete
    suspend fun deleteFromCart(entity: CartEntity)

    @Query("DELETE * FROM cart")
    suspend fun deleteAllFromCart(entity: CartEntity)


}