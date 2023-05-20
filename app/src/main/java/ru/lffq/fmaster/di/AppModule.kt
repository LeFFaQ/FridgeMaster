package ru.lffq.fmaster.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.lffq.fmaster.common.connection.ConnectionObserver
import ru.lffq.fmaster.common.connection.NetworkConnectionObserver
import ru.lffq.fmaster.feature_feed.domain.FeedUseCases
import ru.lffq.fmaster.feature_inventory.data.InventoryRepository
import ru.lffq.fmaster.feature_inventory.data.inventory.InventoryDB
import ru.lffq.fmaster.feature_inventory.domain.IInventoryRepository
import ru.lffq.fmaster.feature_inventory.domain.usecase.InventoryUseCases
import ru.lffq.fmaster.feature_rskrf.data.remote.RskrfApi
import ru.lffq.fmaster.feature_rskrf.data.remote.RskrfRepository
import ru.lffq.fmaster.feature_rskrf.domain.IRskrfRepository
import ru.lffq.fmaster.feature_rskrf.domain.usecase.GetArticleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkConnectionObserver(@ApplicationContext context: Context): ConnectionObserver {
        return NetworkConnectionObserver(context)
    }

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

    @Provides
    @Singleton
    fun provideRskrfApi(): RskrfApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .baseUrl("https://rskrf.ru/")
            .build()
            .create(RskrfApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRskrfRepository(api: RskrfApi): IRskrfRepository {
        return RskrfRepository(api)
    }

    @Provides
    @Singleton
    fun provideFeedUseCases(repository: IRskrfRepository): FeedUseCases {
        return FeedUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideGetArticleUseCase(repository: IRskrfRepository): GetArticleUseCase {
        return GetArticleUseCase(repository)
    }
}
