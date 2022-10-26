package com.rivaldy.id.mvvmtemplateapp.di

import com.rivaldy.id.mvvmtemplateapp.data.AppDataManager
import com.rivaldy.id.mvvmtemplateapp.data.DataRepository
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import com.rivaldy.id.mvvmtemplateapp.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDataRepository(appDataManager: AppDataManager): DataRepository {
        return DataRepository(appDataManager)
    }

    @Provides
    fun provideMovieRepositoryImpl(apiService: ApiService, db: AppDatabase): MovieRepositoryImpl {
        return MovieRepositoryImpl(apiService, db)
    }
}