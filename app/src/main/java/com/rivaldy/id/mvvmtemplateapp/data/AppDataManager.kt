package com.rivaldy.id.mvvmtemplateapp.data

import androidx.lifecycle.LiveData
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.AppPreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.remote.AppApiHelper
import javax.inject.Inject

/**
 * Created by rivaldy on 10/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class AppDataManager @Inject constructor(
    private val api: AppApiHelper,
    private val db: AppDbHelper,
    private val pref: AppPreferencesHelper
) : DataManager {

    /** Remote Data - Fetch API **/
    override suspend fun getMoviesApiCall(): MovieResponse {
        return api.getMoviesApiCall()
    }

    /** Local Data - Room Local Storage **/
    override suspend fun clearMoviesDb() {
        db.clearMoviesDb()
    }

    override suspend fun insertAllMovieDb(movies: MutableList<MovieEntity>) {
        db.insertAllMovieDb(movies)
    }

    override suspend fun insertMovieDb(movie: MovieEntity) {
        db.insertMovieDb(movie)
    }

    override fun getAllMovieDb(): LiveData<MutableList<MovieEntity>> {
        return db.getAllMovieDb()
    }

    override fun getMovieByIdDb(movieId: Int): LiveData<MovieEntity> {
        return db.getMovieByIdDb(movieId)
    }

    override suspend fun deleteMovieDb(movie: MovieEntity) {
        db.deleteMovieDb(movie)
    }

    override suspend fun deleteMovieByIdDb(movieId: Int) {
        db.deleteMovieByIdDb(movieId)
    }

    /** Local Data - SharedPreference Storage **/

    override fun setAccessTokenPref(token: String) {
        pref.setAccessTokenPref(token)
    }

    override fun getAccessTokenPref(): String {
        return pref.getAccessTokenPref()
    }

    override fun setCurrentUserIdPref(id: String) {
        return pref.setCurrentUserIdPref(id)
    }

    override fun getCurrentUserIdPref(): String {
        return pref.getCurrentUserIdPref()
    }

    override fun setFullMoviePref(movie: MovieLocaleData) {
        setAccessTokenPref(movie.title ?: "")
        setCurrentUserIdPref(movie.description ?: "")
    }

    fun getDataUserPref(): AppPreferencesHelper {
        return pref
    }
}