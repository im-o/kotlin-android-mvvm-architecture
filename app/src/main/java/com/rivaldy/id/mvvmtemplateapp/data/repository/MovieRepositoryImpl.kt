package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.paging.MoviePagingSource.Companion.ITEMS_PER_PAGE
import com.rivaldy.id.mvvmtemplateapp.data.paging.MovieRemoteMediator
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import javax.inject.Inject

/** Created by github.com/im-o on 9/16/2022. */

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase
) : MovieRepository {
    override fun getMoviesByPage(): LiveData<PagingData<MovieEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = MovieRemoteMediator(apiService, db),
            pagingSourceFactory = {
                db.movieDao().getMoviesPaging()
            }
        ).liveData
    }
}