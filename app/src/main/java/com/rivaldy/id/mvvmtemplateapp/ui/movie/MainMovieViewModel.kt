package com.rivaldy.id.mvvmtemplateapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@HiltViewModel
class MainMovieViewModel @Inject constructor(
    repo: MovieRepositoryImpl
) : ViewModel() {

    val moviesApiCall: LiveData<PagingData<MovieEntity>> = repo.getMoviesByPage().cachedIn(viewModelScope)
}