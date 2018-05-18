package com.example.ebpearls.kotlin_room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.ebpearls.kotlin_room.dao.DaoAccess
import com.example.ebpearls.kotlin_room.entities.MovieData

@Database(entities = [(MovieData::class)],version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun daoAccess():DaoAccess

    companion object {

        private var INSTANCE:MovieDatabase? = null
        private val DB_NAME:String = "movies"

        fun getInstance(contex:Context):MovieDatabase?{

            if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(contex.getApplicationContext(), MovieDatabase::class.java, DB_NAME).addMigrations().build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}