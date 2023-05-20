package ru.lffq.fmaster.feature_inventory.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.lffq.fmaster.feature_inventory.data.cart.CartEntity
import ru.lffq.fmaster.feature_inventory.domain.ICartRepository

class CartUseCases(
    private val repository: ICartRepository
) {

    fun getCart(): Flow<List<CartEntity>> {
        return repository.getCart()
    }

    suspend fun insertToCart(entity: CartEntity) {
        repository.insertToCart(entity)
    }

    suspend fun deleteAllFromCart() {
        repository.deleteAllFromCart()
    }

    suspend fun deleteFromCart(entity: CartEntity) {
        repository.deleteFromCart(entity)
    }


}