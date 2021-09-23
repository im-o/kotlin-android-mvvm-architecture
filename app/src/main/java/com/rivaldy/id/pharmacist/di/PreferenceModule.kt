package com.rivaldy.id.pharmacist.di

import android.content.Context
import android.content.SharedPreferences
import com.rivaldy.id.pharmacist.utils.UtilConstants.SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by rivaldy on 28/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@InstallIn(ApplicationComponent::class)
@Module
object PreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context) : SharedPreferences{
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}