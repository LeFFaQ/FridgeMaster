package ru.lffq.fmaster.feature_rskrf.domain.cache

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.RoomDatabase

abstract class CategoryCache : RoomDatabase() {
    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "category_cache"
    }
}

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertCategoryToCache(entity: CategoryEntity)
}

@Entity(CategoryCache.DATABASE_NAME)
data class CategoryEntity(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val icon: String
)