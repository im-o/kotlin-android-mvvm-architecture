package com.rivaldy.id.pharmacist.data.remote

import com.rivaldy.id.pharmacist.data.model.api.movie.MovieResponse

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface ApiHelper {
    suspend fun getMoviesApiCall(): MovieResponse
}