package com.rivaldy.id.mvvmtemplateapp.data.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by rivaldy on 19/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

class DataStoreManager(val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

    companion object {
        const val USER_DATASTORE = "movie_datastore"
        private val ACCESS_TOKEN = stringPreferencesKey("PREF_ACCESS_TOKEN")
        private val USER_ID = stringPreferencesKey("PREF_USER_ID")
    }

    suspend fun saveToDataStore(movie: MovieLocaleData) {
        // Only sample
        context.dataStore.edit {
            it[ACCESS_TOKEN] = movie.title.toString()
            it[USER_ID] = movie.description.toString()
        }
    }

    suspend fun getFromDataStore(): Flow<MovieLocaleData> {
        // Only sample
        return context.dataStore.data.map {
            MovieLocaleData(
                title = it[ACCESS_TOKEN] ?: "",
                description = it[USER_ID] ?: ""
            )
        }
    }
}