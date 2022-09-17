package com.rivaldy.id.mvvmtemplateapp.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@HiltViewModel
class MainMovieViewModel @Inject constructor(
    private val repo: MovieRepositoryImpl
) : ViewModel() {

    private lateinit var _moviesFlow: Flow<PagingData<MovieLocaleData>>
    val moviesFlow: Flow<PagingData<MovieLocaleData>>
        get() = _moviesFlow

    fun getMoviesByPageApiCall() = viewModelScope.launch {
        _moviesFlow = repo.getMoviesByPage().cachedIn(viewModelScope)
    }
}