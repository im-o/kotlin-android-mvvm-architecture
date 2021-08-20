package com.rivaldy.id.mvvmtemplateapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.data.DataRepository
import com.rivaldy.id.mvvmtemplateapp.utils.UtilCoroutines.io
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MovieViewModel @ViewModelInject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _movies: MutableLiveData<DataResource<MovieResponse>> = MutableLiveData()
    val movies: LiveData<DataResource<MovieResponse>> = _movies

    fun getMoviesApiCall() = viewModelScope.launch {
        _movies.value = DataResource.Loading
        _movies.value = dataRepository.getMoviesApiCall()
    }

    fun getMoviesLocal() = dataRepository.getMoviesLocal()

    fun insertMoviesLocal(movieResponse: MovieResponse) {
        val moviesData = mutableListOf<MovieEntity>()
        for (data in movieResponse.movieResults ?: return) {
            val movieEntity = MovieEntity(data.id ?: 0, data.title ?: "", data.releaseDate, data.voteAverage, data.posterPath, data.overview, data.originalLanguage)
            moviesData.add(movieEntity)
        }
        io { dataRepository.insertMoviesLocal(moviesData) }
    }

    fun clearMovies() {
        io { dataRepository.clearMovies() }
    }
}