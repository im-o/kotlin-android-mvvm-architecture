package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity

/** Created by github.com/im-o on 9/16/2022. */

interface MovieRepository {
    fun getMoviesByPage(): LiveData<PagingData<MovieEntity>>
}