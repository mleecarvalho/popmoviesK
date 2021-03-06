package com.example.marcio.popmoviesk.dashboard

import android.os.AsyncTask
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection

/**
 * Created by marcio on 18/01/2018.
 */
class ListMovieAsyncTask(private var asyncTask: ListMovieContract.AsyncTask) : AsyncTask<ListMovieOrderBy, Void, ArrayList<Movie>>() {

    private val dbConnection: MovieDBConnection = MovieDBConnection()

    override fun onPreExecute() {
        super.onPreExecute()
        this.asyncTask.processStart()
    }

    override fun doInBackground(vararg list: ListMovieOrderBy?): ArrayList<Movie>? {
       return dbConnection.requestMovies(list[0]!!)
    }

    override fun onPostExecute(movies: ArrayList<Movie>) {
        super.onPostExecute(movies)
        this.asyncTask.processFinish(movies)
    }

}