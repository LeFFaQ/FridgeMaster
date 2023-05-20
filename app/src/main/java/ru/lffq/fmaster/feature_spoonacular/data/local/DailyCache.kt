package ru.lffq.fmaster.feature_spoonacular.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import ru.lffq.fmaster.dataStore
import ru.lffq.fmaster.feature_spoonacular.data.remote.dto.Random
import java.io.File


class DailyCache(private val context: Context) {

    private val gson = Gson()

    suspend fun writeToCache(recipe: Random.Recipe) {
        Log.d("CACHE", "readFromCache: ${cachePresented()}")

        val instant =
            kotlinx.datetime.Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val instantSum = instant.dayOfMonth + instant.year

        var date: LocalDateTime =
            Instant.fromEpochMilliseconds(0).toLocalDateTime(TimeZone.currentSystemDefault())


        context.dataStore.edit {
            Log.d("CACHE", "writeToCache: setting date variable")
            date = Instant.fromEpochMilliseconds(it[DATASTORE_DATE_KEY] ?: 0)
                .toLocalDateTime(TimeZone.currentSystemDefault())

        }
        val dateSum = date.dayOfMonth + date.year

        val cache = File(context.cacheDir, CACHE_FILE_NAME)

        if (!cache.exists()) {
            Log.d("CACHE", "writeToCache: file created")
            withContext(Dispatchers.IO) {
                cache.createNewFile()
            }
        }


        if (instantSum != dateSum) {
            Log.d("CACHE", "writeToCache: writing Daily to cache")

            cache.writeText(gson.toJson(recipe))
            Log.d("CACHE", "file existence = ${cache.exists()}")
            Log.d("CACHE", "something in file = ${cache.length() != 0L}")
            Log.d("CACHE", "writeToCache: ${cache.readText()}")
            context.dataStore.edit {
                Log.d("CACHE", "writeToCache: setting DATASTORE_DATE_KEY")
                it[DATASTORE_DATE_KEY] =
                    instant.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
            }
        }


    }

    fun readFromCache(): Random.Recipe {
        val file = File(context.cacheDir, CACHE_FILE_NAME)
        Log.d("CACHE", "readFromCache: ${cachePresented()}")

        return gson.fromJson(file.readText(), Random.Recipe::class.java)
    }

    fun cachePresented(): Boolean {
        val date: Long = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getLong((DATASTORE_DATE_KEY.name), 0)
        return date != 0L
    }

    companion object {
        const val CACHE_FILE_NAME = "daily_cache"
        val DATASTORE_DATE_KEY = longPreferencesKey("datastore_daily_cache_time")
    }
}