package com.rivaldy.id.mvvmtemplateapp.data

import com.rivaldy.id.mvvmtemplateapp.data.local.db.DbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.PreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiHelper

/**
 * Created by rivaldy on 10/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface DataManager : DbHelper, PreferencesHelper, ApiHelper {
    fun setFullMoviePref(movie: MovieLocaleData){
        setAccessTokenPref(movie.title ?: "")
        setCurrentUserIdPref(movie.description ?: "")
    }
}