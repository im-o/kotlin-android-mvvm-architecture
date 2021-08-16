package com.rivaldy.id.mvvmtemplateapp.data

import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.db.DbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.AppPreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.PreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiHelper
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

    /** Local Data - Room Local Storage **/
    override suspend fun clearMovies() {
        db.clearMovies()
    }

    override suspend fun insertAllMovie(movies: MutableList<MovieEntity>) {
        db.insertAllMovie(movies)
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        db.insertMovie(movie)
    }

    override fun getAllMovie() = db.getAllMovie()

    override fun getMovieById(movieId: Int) = db.getMovieById(movieId)

    override suspend fun deleteMovie(movie: MovieEntity) {
        db.deleteMovie(movie)
    }

    override suspend fun deleteMovieById(movieId: Int) {
        db.deleteMovieById(movieId)
    }

    /** Remote Data - Fetch API **/
    override suspend fun getMoviesApiCall() = api.getMoviesApiCall()

}