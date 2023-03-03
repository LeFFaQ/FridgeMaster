package ru.lffq.fmaster.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.lffq.fmaster.feature_inventory.data.InventoryDB
import ru.lffq.fmaster.feature_inventory.data.InventoryRepository
import ru.lffq.fmaster.feature_inventory.domain.IInventoryRepository
import ru.lffq.fmaster.feature_inventory.domain.InventoryUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInventoryDB(app: Application): InventoryDB {
        return Room.databaseBuilder(app, InventoryDB::class.java, InventoryDB.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(db: InventoryDB): IInventoryRepository {
        return InventoryRepository(db.inventoryDao)
    }

    @Provides
    @Singleton
    fun provideInventoryUseCases(repository: IInventoryRepository): InventoryUseCases {
        return InventoryUseCases(repository)
    }
}
