package com.rivaldy.id.mvvmtemplateapp.data.model.db.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.jetbrains.annotations.NotNull

/**
 * Created by rivaldy on 05/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

@Entity(tableName = "tbl_movie", primaryKeys = ["id"])
data class MovieEntity(

    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = 0.0,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "info_genre")
    val infoGenre: String? = null,
)