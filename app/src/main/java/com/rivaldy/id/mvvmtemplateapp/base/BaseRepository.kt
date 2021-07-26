package com.rivaldy.id.mvvmtemplateapp.base

import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.NO_INTERNET
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.OTHER_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): DataResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        DataResource.Failure(false, throwable.code(), throwable.response()?.errorBody(), throwable.message)
                    }
                    else -> {
                        if (throwable.message == NO_INTERNET) {
                            DataResource.Failure(true, null, null, throwable.message)
                        } else DataResource.Failure(true, OTHER_ERROR, null, throwable.message) // CHANGE THIS TO FALSE
                    }
                }
            }
        }
    }
}