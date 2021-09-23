package com.rivaldy.id.pharmacist.data.remote

import com.rivaldy.id.pharmacist.data.model.api.movie.MovieResponse
import retrofit2.http.GET

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovies(): MovieResponse
}