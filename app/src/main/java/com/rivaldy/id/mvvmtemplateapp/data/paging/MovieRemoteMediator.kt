package com.rivaldy.id.mvvmtemplateapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService

/** Created by github.com/im-o on 10/26/2022. */

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val apiService: ApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = INITIAL_PAGE_INDEX
        try {
            val responseData = apiService.getMoviesByPage(page) // if you can limit the data use this -> apiService.getMoviesByPage(page, state.config.pageSize)
            val endOfPaginationReached = responseData.movieResults.isNullOrEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.movieDao().deleteMovies()
                }
                db.movieDao().insertAllMovie(
                    responseData.movieResults?.map {
                        MovieEntity(it.id ?: 0, it.title, it.releaseDate, it.voteAverage, it.backdropPath, it.overview, it.overview)
                    }?.toMutableList() ?: mutableListOf()
                )
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}