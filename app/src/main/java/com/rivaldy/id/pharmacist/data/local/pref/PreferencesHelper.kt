package com.rivaldy.id.pharmacist.data.local.pref

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