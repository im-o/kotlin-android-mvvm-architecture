package com.rivaldy.id.mvvmtemplateapp.data

import com.rivaldy.id.mvvmtemplateapp.base.BaseRepository
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import javax.inject.Inject

/**
 * Created by rivaldy on 03/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class DataRepository @Inject constructor(
    private val appDataManager: AppDataManager
) : BaseRepository() {

    /** Remote Data - Fetch API **/
    suspend fun getMoviesApiCall() = safeApiCall {
        appDataManager.getMoviesApiCall()
    }

    /** Local Data - Room Local Storage **/
    fun getAllMovieDb() = appDataManager.getAllMovieDb()

    fun getMoviesPaging() = appDataManager.getMoviesPaging()

    suspend fun insertMoviesLocal(movies: MutableList<MovieEntity>) = appDataManager.insertAllMovieDb(movies)

    suspend fun clearMovies() = appDataManager.clearMoviesDb()

    /** Local Data - SharedPreference Storage **/

    fun setFullMoviePref(movie: MovieLocaleData) {
        appDataManager.setFullMoviePref(movie)
    }

    fun getAccessTokenPref() = appDataManager.getAccessTokenPref()

    fun getCurrentUserIdPref() = appDataManager.getCurrentUserIdPref()

    fun getDataUserPref() = appDataManager.getDataUserPref()
}
