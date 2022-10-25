package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.paging.MoviesPagingSource
import com.rivaldy.id.mvvmtemplateapp.data.paging.MoviesPagingSource.Companion.ITEMS_PER_PAGE
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import javax.inject.Inject

/** Created by github.com/im-o on 9/16/2022. */

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override fun getMoviesByPage(): LiveData<PagingData<MovieLocaleData>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { MoviesPagingSource(apiService) }
        ).liveData
    }
}