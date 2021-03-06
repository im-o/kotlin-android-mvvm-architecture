package com.rivaldy.id.mvvmtemplateapp.data.local.pref

import com.rivaldy.id.mvvmtemplateapp.data.model.pref.MoviePref
import kotlinx.coroutines.flow.Flow

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface PreferencesHelper {
    fun setAccessTokenPref(token: String) //sample this title
    fun getAccessTokenPref(): String
    fun setCurrentUserIdPref(id: String) //sample this desc
    fun getCurrentUserIdPref(): String
}