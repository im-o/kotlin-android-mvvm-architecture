package com.rivaldy.id.mvvmtemplateapp.data.local.pref

import com.rivaldy.id.mvvmtemplateapp.data.model.pref.MoviePref
import kotlinx.coroutines.flow.Flow

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface PreferencesHelper {
    fun setAccessToken(token: String)
    fun getAccessToken(): String
    fun setCurrentUserId(id: String)
    fun getCurrentUserId(): String
}