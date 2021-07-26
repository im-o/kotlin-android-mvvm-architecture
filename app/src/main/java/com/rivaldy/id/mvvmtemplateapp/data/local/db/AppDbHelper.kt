package com.rivaldy.id.mvvmtemplateapp.data.local.db

import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import javax.inject.Inject

/**
 * Created by rivaldy on 06/07/21
 * Find me on my Github -> https://github.com/im-o
 **/


class AppDbHelper @Inject constructor(
    private val appDatabase: AppDatabase
) : DbHelper {

    override suspend fun clearMovies() {
        appDatabase.movieDao().clearMovies()
    }

    override suspend fun insertAllMovie(movies: MutableList<MovieEntity>) {
        appDatabase.movieDao().insertAllMovie(movies)
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        appDatabase.movieDao().insertMovie(movie)
    }

    override fun getAllMovie() = appDatabase.movieDao().getAllMovie()

    override fun getMovieById(movieId: Int) = appDatabase.movieDao().getMovieById(movieId)

    override suspend fun deleteMovie(movie: MovieEntity) {
        appDatabase.movieDao().deleteMovie(movie)
    }

    override suspend fun deleteMovieById(movieId: Int) {
        appDatabase.movieDao().deleteMovieById(movieId)
    }

}