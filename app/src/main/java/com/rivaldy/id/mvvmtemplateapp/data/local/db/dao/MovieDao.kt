package com.rivaldy.id.mvvmtemplateapp.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@Dao
interface MovieDao {

    @Query("DELETE FROM tbl_movie")
    suspend fun clearMovies()

    /** if some data is same/conflict, it'll be replace with new data. **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movie: MutableList<MovieEntity>)

    /**
     * why not use suspend ? because Room does not support LiveData with suspended functions.
     * LiveData already works on a background thread and should be used directly without using coroutines
     **/

    @Query("SELECT * FROM tbl_movie ORDER BY title ASC")
    fun getAllMovie(): LiveData<MutableList<MovieEntity>>

    @Query("SELECT * FROM tbl_movie WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("DELETE FROM tbl_movie")
    suspend fun deleteMovies()

    /** you can use this too, for delete note by id. **/
    @Query("DELETE FROM tbl_movie WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT * FROM tbl_movie")
    fun getMoviesPaging(): PagingSource<Int, MovieEntity>
}