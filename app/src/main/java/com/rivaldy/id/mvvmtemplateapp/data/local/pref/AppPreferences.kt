package com.rivaldy.id.mvvmtemplateapp.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class AppPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val DATA_STORE_NAME = "movie_data_store"
        const val PREF_KEY_TOKEN = "PREF_KEY_TOKEN"
    }

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
    private val appContext = context.applicationContext
    //private val dataStore: DataStore<Preferences> = context.dataStore(name = DATA_STORE_NAME)
}