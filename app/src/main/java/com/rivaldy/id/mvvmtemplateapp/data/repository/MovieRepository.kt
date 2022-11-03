package com.rivaldy.id.mvvmtemplateapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import kotlinx.coroutines.flow.Flow

/** Created by github.com/im-o on 9/16/2022. */

interface MovieRepository {
    fun getMoviesByPage(): LiveData<PagingData<MovieLocaleData>>
}