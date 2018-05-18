package com.example.ebpearls.kotlin_room.dao

import android.arch.persistence.room.*
import com.example.ebpearls.kotlin_room.entities.MovieData

@Dao
interface DaoAccess {

    @Query("SELECT * FROM MOVIE_DATA")
    fun getAll():List<MovieData>

    @Insert
    fun insertSingleMovieDetail(movieData:MovieData)

    @Update
    fun updateMovieData(movieData: MovieData)

    @Delete
    fun deleteMovieData(movieData: MovieData)

    @Query("SELECT * FROM MOVIE_DATA WHERE movie_id = :movieId")
    fun getSingleMovie(movieId:Int):MovieData
}