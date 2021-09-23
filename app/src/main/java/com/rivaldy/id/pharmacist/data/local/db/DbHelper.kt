package com.rivaldy.id.pharmacist.data.local.db

import androidx.lifecycle.LiveData
import com.rivaldy.id.pharmacist.data.model.db.movie.MovieEntity

/**
 * Created by rivaldy on 06/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface DbHelper {
    suspend fun clearMovies()
    suspend fun insertAllMovie(movies: MutableList<MovieEntity>)
    suspend fun insertMovie(movie: MovieEntity)
    fun getAllMovie(): LiveData<MutableList<MovieEntity>>
    fun getMovieById(movieId: Int): LiveData<MovieEntity>
    suspend fun deleteMovie(movie: MovieEntity)
    suspend fun deleteMovieById(movieId: Int)
}