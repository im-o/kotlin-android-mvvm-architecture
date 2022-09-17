package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.paging.MoviesPagingSource
import com.rivaldy.id.mvvmtemplateapp.data.paging.MoviesPagingSource.Companion.ITEMS_PER_PAGE
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Created by github.com/im-o on 9/16/2022. */

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getMoviesByPage(): Flow<PagingData<MovieLocaleData>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService) }
        ).flow
    }
}