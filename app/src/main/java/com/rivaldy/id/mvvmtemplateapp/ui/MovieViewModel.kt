package com.rivaldy.id.mvvmtemplateapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldy.id.mvvmtemplateapp.data.DataRepository
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.AppPreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.utils.UtilCoroutines.io
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _movies: MutableLiveData<DataResource<MovieResponse>> = MutableLiveData()
    private val _getDataUserPref: MutableLiveData<AppPreferencesHelper> = MutableLiveData()
    val movies: LiveData<DataResource<MovieResponse>> = _movies
    val getDataUserPref: LiveData<AppPreferencesHelper> = _getDataUserPref

    fun getMoviesApiCall() = viewModelScope.launch {
        _movies.value = DataResource.Loading
        _movies.value = dataRepository.getMoviesApiCall()
    }

    fun getMoviesLocal() = dataRepository.getAllMovieDb()

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

    fun setFullMoviePref(movie: MovieLocaleData) {
        dataRepository.setFullMoviePref(movie)
        getDataUserPref()
    }

    fun getDataUserPref() {
        _getDataUserPref.value = dataRepository.getDataUserPref()
    }
}