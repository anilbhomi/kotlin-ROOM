package com.example.ebpearls.kotlin_room

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.ebpearls.kotlin_room.database.MovieDatabase
import com.example.ebpearls.kotlin_room.entities.MovieData
import com.example.ebpearls.kotlin_room.handlers.DbWorkerThread

class MainActivity : AppCompatActivity() {

    private var mDb: MovieDatabase? = null

    private lateinit var mDbWorkerThread: DbWorkerThread

    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startThread()

        initDatabase()

        insertMovie()

        getAllMovies()
    }

    fun startThread() {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
    }

    fun initDatabase() {
        mDb = MovieDatabase.getInstance(this)
    }

    fun insertMovie() {
        val task = Runnable {
            val movieData = MovieData(2, 200, "anil")
            mDb?.daoAccess()?.insertSingleMovieDetail(movieData)
        }
        mDbWorkerThread.postTask(task)
    }

    private fun getAllMovies() {
        val task = Runnable {
            val movieList: List<MovieData> = mDb?.daoAccess()?.getAll()!!
            for (i in movieList.indices) {
                Log.i("DATA", " ${movieList.get(i).movieId} and ${ movieList.get(i).movieName}")
            }
        }
        mDbWorkerThread.postTask(task)
    }

    override fun onDestroy() {
        MovieDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}
