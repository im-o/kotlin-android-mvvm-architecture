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

    override suspend fun clearMoviesDb() {
        appDatabase.movieDao().clearMovies()
    }

    override suspend fun insertAllMovieDb(movies: MutableList<MovieEntity>) {
        appDatabase.movieDao().insertAllMovie(movies)
    }

    override suspend fun insertMovieDb(movie: MovieEntity) {
        appDatabase.movieDao().insertMovie(movie)
    }

    override fun getAllMovieDb() = appDatabase.movieDao().getAllMovie()

    override fun getMovieByIdDb(movieId: Int) = appDatabase.movieDao().getMovieById(movieId)

    override suspend fun deleteMovieDb(movie: MovieEntity) {
        appDatabase.movieDao().deleteMovie(movie)
    }

    override suspend fun deleteMovieByIdDb(movieId: Int) {
        appDatabase.movieDao().deleteMovieById(movieId)
    }

}