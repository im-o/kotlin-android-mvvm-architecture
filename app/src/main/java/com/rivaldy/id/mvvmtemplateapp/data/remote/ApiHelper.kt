package com.rivaldy.id.mvvmtemplateapp.data.remote

import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface ApiHelper {
    suspend fun getMoviesApiCall(): MovieResponse
}