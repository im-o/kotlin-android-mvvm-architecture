package com.rivaldy.id.pharmacist.data.remote

import javax.inject.Inject

/**
 * Created by rivaldy on 07/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class AppApiHelper @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getMoviesApiCall() = apiService.getMovies()
}