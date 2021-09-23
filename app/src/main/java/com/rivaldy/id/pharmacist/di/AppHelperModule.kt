package com.rivaldy.id.pharmacist.di

import android.content.SharedPreferences
import com.rivaldy.id.pharmacist.data.AppDataManager
import com.rivaldy.id.pharmacist.data.local.db.AppDatabase
import com.rivaldy.id.pharmacist.data.local.db.AppDbHelper
import com.rivaldy.id.pharmacist.data.local.pref.AppPreferencesHelper
import com.rivaldy.id.pharmacist.data.remote.ApiService
import com.rivaldy.id.pharmacist.data.remote.AppApiHelper
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
    fun provideAppPreferencesHelper(sharedPref: SharedPreferences) = AppPreferencesHelper(sharedPref)

    @Provides
    fun providesAppApiHelper(apiService: ApiService) = AppApiHelper(apiService)

    @Provides
    fun providesAppDataManager(appApiHelper: AppApiHelper, appDbHelper: AppDbHelper, appPreferencesHelper: AppPreferencesHelper): AppDataManager {
        return AppDataManager(appApiHelper, appDbHelper, appPreferencesHelper)
    }
}