package com.rivaldy.id.mvvmtemplateapp.data

import com.rivaldy.id.mvvmtemplateapp.base.BaseRepository
import com.rivaldy.id.mvvmtemplateapp.data.AppDataManager
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import javax.inject.Inject

/**
 * Created by rivaldy on 03/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class DataRepository @Inject constructor(
    private val appDataManager: AppDataManager
) : BaseRepository() {

    suspend fun getMoviesApiCall() = safeApiCall {
        appDataManager.getMoviesApiCall()
    }

    fun getMoviesLocal() = appDataManager.getAllMovie()

    suspend fun insertMoviesLocal(movies: MutableList<MovieEntity>) = appDataManager.insertAllMovie(movies)

    suspend fun clearMovies() = appDataManager.clearMovies()
}
