package com.example.marcio.popmoviesk.dashboard

import android.os.AsyncTask
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection

/**
 * Created by marcio on 18/01/2018.
 */
class ListMovieAsyncTask: AsyncTask<ListMovieOrderBy, Void, ArrayList<Movie>> {

    private lateinit var asyncTask: ListMovieContract.AsyncTask
    private var dbConnection: MovieDBConnection = MovieDBConnection().getInstance()

    constructor(asyncTask: ListMovieContract.AsyncTask){
        this.asyncTask = asyncTask
    }

    override fun onPreExecute() {
        super.onPreExecute()
        this.asyncTask.processStart()
    }

    override fun doInBackground(vararg list: ListMovieOrderBy?): ArrayList<Movie>? {
       val type: ListMovieOrderBy = list[0] as ListMovieOrderBy
       var moveList: ArrayList<Movie>? = null
       return dbConnection.requestMovies(type)
    }

    override fun onPostExecute(movies: ArrayList<Movie>) {
        super.onPostExecute(movies)
        this.asyncTask.processFinish(movies)
    }

}