package com.rivaldy.id.mvvmtemplateapp.di

import android.content.Context
import androidx.room.Room
import com.rivaldy.id.mvvmtemplateapp.data.local.db.AppDatabase
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.DB_THE_MOVIE_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_THE_MOVIE_DB).build()
    }
}