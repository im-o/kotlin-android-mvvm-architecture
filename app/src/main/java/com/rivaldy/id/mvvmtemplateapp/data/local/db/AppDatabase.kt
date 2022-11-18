package com.rivaldy.id.mvvmtemplateapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldy.id.mvvmtemplateapp.data.local.db.dao.MovieDao
import com.rivaldy.id.mvvmtemplateapp.data.local.db.remotekey.RemoteKeys
import com.rivaldy.id.mvvmtemplateapp.data.local.db.remotekey.RemoteKeysDao
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@Database(
    entities = [MovieEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}