package ru.lffq.fmaster.feature_spoonacular

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.lffq.fmaster.feature_spoonacular.data.local.DailyCache
import ru.lffq.fmaster.feature_spoonacular.data.remote.SpoonacularAPI
import ru.lffq.fmaster.feature_spoonacular.data.remote.SpoonacularRepository
import ru.lffq.fmaster.feature_spoonacular.domain.ISpoonacularRepository
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.GetRecipeInformationUseCase
import ru.lffq.fmaster.feature_spoonacular.domain.use_case.RandomRecipeUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpoonacularModule {

    @Provides
    @Singleton
    fun provideSpoonacularApi(): SpoonacularAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.spoonacular.com/")
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .build()
            .create(SpoonacularAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSpoonacularRepository(api: SpoonacularAPI): ISpoonacularRepository {
        return SpoonacularRepository(api)
    }

    @Provides
    @Singleton
    fun provideRandomRecipeUseCase(repository: ISpoonacularRepository): RandomRecipeUseCase {
        return RandomRecipeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeInformationUseCase(repository: ISpoonacularRepository): GetRecipeInformationUseCase {
        return GetRecipeInformationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDailyCache(@ApplicationContext context: Context): DailyCache {
        return DailyCache(context)
    }

}