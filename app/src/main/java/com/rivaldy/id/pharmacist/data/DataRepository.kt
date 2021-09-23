package com.rivaldy.id.pharmacist.data

import com.rivaldy.id.pharmacist.base.BaseRepository
import com.rivaldy.id.pharmacist.data.model.db.movie.MovieEntity
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
    fun getMoviesLocal() = appDataManager.getAllMovie()

    suspend fun insertMoviesLocal(movies: MutableList<MovieEntity>) = appDataManager.insertAllMovie(movies)

    suspend fun clearMovies() = appDataManager.clearMovies()

    /** Local Data - SharedPreference Storage **/
    fun getAccessToken() = appDataManager.getAccessToken()

    fun getCurrentUserId() = appDataManager.getCurrentUserId()
}
