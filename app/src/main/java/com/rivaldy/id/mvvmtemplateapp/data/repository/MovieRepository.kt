package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.paging.PagingData
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import kotlinx.coroutines.flow.Flow

/** Created by github.com/im-o on 9/16/2022. */

interface MovieRepository {
    suspend fun getMoviesByPage(): Flow<PagingData<MovieLocaleData>>
}