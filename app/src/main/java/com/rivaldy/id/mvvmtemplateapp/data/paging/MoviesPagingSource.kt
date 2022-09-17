package com.rivaldy.id.mvvmtemplateapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException

/** Created by github.com/im-o on 9/16/2022. */

class MoviesPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, MovieLocaleData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieLocaleData> {
        val startPage = params.key ?: TMDB_STARTING_PAGE
        //val range = startPage.until(startPage + params.loadSize)
        return try {
            val response = apiService.getMoviesByPage(startPage)
            val movies = response.movieResults
            //val nextKey = if (movies?.isEmpty() == true) null else pageNumber + (params.loadSize / NETWORK_PAGE_SIZE)
            val nextKey = if (movies?.isEmpty() == true) null else startPage + TMDB_STARTING_PAGE
            LoadResult.Page(
                data = movies?.map {
                    MovieLocaleData(it.id, it.title, it.overview, it.posterPath)
                }.orEmpty(),
//                prevKey = when (startPage) {
//                    TMDB_STARTING_PAGE_NUMBER -> null
//                    else -> ensureValidKey(key = range.first - params.loadSize)
//                },
                prevKey = if (startPage == TMDB_STARTING_PAGE) null else startPage,
                //nextKey = startPage + 1
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieLocaleData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    //private fun ensureValidKey(key: Int) = max(TMDB_STARTING_PAGE_NUMBER, key)

    companion object {
        const val TMDB_STARTING_PAGE = 1
        const val ITEMS_PER_PAGE = 20
    }
}