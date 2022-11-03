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
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getMoviesByPage(page)
            val movies = response.movieResults
            LoadResult.Page(
                data = movies?.map {
                    MovieLocaleData(it.id, it.title, it.overview, it.posterPath)
                }.orEmpty(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies?.isEmpty() == true) null else page + 1
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

    companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val ITEMS_PER_PAGE = 20 // load 20 items per page - default is the movie db is 20, cause no limit parameter for this
    }
}