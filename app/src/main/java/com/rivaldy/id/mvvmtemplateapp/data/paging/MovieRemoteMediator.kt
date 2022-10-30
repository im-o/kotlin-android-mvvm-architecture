package com.rivaldy.id.mvvmtemplateapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.data.local.db.remotekey.RemoteKeys
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
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        try {
            val responseData = apiService.getMoviesByPage(page) // apiService.getMoviesByPage(page, state.config.pageSize) -> if you have limit parameter, use this
            val endOfPaginationReached = responseData.movieResults.isNullOrEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().deleteRemoteKeys()
                    db.movieDao().deleteMovies()
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.movieResults?.map {
                    RemoteKeys(id = it.id ?: 0, prevKey = prevKey, nextKey = nextKey)
                }
                db.remoteKeysDao().insertAll(keys ?: mutableListOf())
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

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            db.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            db.remoteKeysDao().getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                db.remoteKeysDao().getRemoteKeysId(id)
            }
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}