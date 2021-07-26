package com.rivaldy.id.mvvmtemplateapp.di

import com.rivaldy.id.mvvmtemplateapp.data.AppDataManager
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.db.DbHelper
import com.rivaldy.id.mvvmtemplateapp.data.local.pref.AppPreferencesHelper
import com.rivaldy.id.mvvmtemplateapp.data.remote.ApiService
import com.rivaldy.id.mvvmtemplateapp.data.remote.AppApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by rivaldy on 10/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@Module
@InstallIn(ApplicationComponent::class)
object AppHelperModule {

    @Provides
    fun provideAppDbHelper(appDatabase: AppDatabase) = AppDbHelper(appDatabase)

    @Provides
    fun provideAppPreferencesHelper() = AppPreferencesHelper()

    @Provides
    fun providesAppApiHelper(apiService: ApiService) = AppApiHelper(apiService)

    @Provides
    fun providesAppDataManager(appDbHelper: AppDbHelper, appPreferencesHelper: AppPreferencesHelper, appApiHelper: AppApiHelper) : AppDataManager {
        return AppDataManager(appDbHelper, appPreferencesHelper, appApiHelper)
    }
}