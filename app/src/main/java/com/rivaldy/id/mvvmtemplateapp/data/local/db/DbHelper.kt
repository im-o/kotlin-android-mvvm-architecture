package com.rivaldy.id.mvvmtemplateapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity

/**
 * Created by rivaldy on 06/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface DbHelper {
    suspend fun clearMoviesDb()
    suspend fun insertAllMovieDb(movies: MutableList<MovieEntity>)
    suspend fun insertMovieDb(movie: MovieEntity)
    fun getAllMovieDb(): LiveData<MutableList<MovieEntity>>
    fun getMoviesPaging(): PagingSource<Int, MovieEntity>
    fun getMovieByIdDb(movieId: Int): LiveData<MovieEntity>
    suspend fun deleteMovieDb(movie: MovieEntity)
    suspend fun deleteMovieByIdDb(movieId: Int)
}