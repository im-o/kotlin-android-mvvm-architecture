package com.rivaldy.id.mvvmtemplateapp.data.local.pref

import android.content.SharedPreferences
import com.rivaldy.id.mvvmtemplateapp.data.model.pref.MoviePref
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/
class AppPreferencesHelper @Inject constructor(
    private val sharedPref: SharedPreferences
) : PreferencesHelper {

    override fun setAccessToken(token: String) {
        sharedPref.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply()
    }

    override fun getAccessToken(): String {
        return sharedPref.getString(PREF_KEY_ACCESS_TOKEN, null) ?: ""
    }

    override fun setCurrentUserId(id: String) {
        sharedPref.edit().putString(PREF_KEY_USER_ID, id).apply()
    }

    override fun getCurrentUserId(): String {
        return sharedPref.getString(PREF_KEY_USER_ID, null) ?: ""
    }

    companion object {
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private const val PREF_KEY_USER_ID = "PREF_KEY_USER_ID"
    }
}