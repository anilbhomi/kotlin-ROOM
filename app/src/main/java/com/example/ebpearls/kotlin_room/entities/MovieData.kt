package com.example.ebpearls.kotlin_room.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movie_data")
class MovieData(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") var id: Long?,
        @ColumnInfo(name = "movie_id") var movieId: Int,
        @ColumnInfo(name = "movie_name") var movieName: String) {}