package com.rivaldy.id.pharmacist.di

import com.rivaldy.id.pharmacist.data.AppDataManager
import com.rivaldy.id.pharmacist.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDataRepository(appDataManager: AppDataManager): DataRepository {
        return DataRepository(appDataManager)
    }
}