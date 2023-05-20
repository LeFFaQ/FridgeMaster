package ru.lffq.fmaster.feature_inventory.domain

import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.feature_inventory.data.cart.CartEntity

interface ICartRepository {

    fun getCart(): Flow<List<CartEntity>>

    suspend fun getFromCartById(id: Int): CartEntity

    suspend fun insertToCart(entity: CartEntity)

    suspend fun deleteFromCart(entity: CartEntity)

    suspend fun deleteAllFromCart()
}