package com.rivaldy.id.pharmacist.data.network

import okhttp3.ResponseBody

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

sealed class DataResource<out T> {
    data class Success<out T>(val value: T): DataResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val otherMessage: String?
    ): DataResource<Nothing>()

    object Loading: DataResource<Nothing>()
}
