package com.example.marcio.popmoviesk.dashboard

import android.content.Context
import android.widget.ImageView
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection
import com.example.marcio.popmoviesk.utils.NetConnection.hasInternetConnection
import com.example.marcio.popmoviesk.utils.NetConnection.showConnectionError

/**
 * Created by marcio on 18/01/2018.
 */
class ListMoviePresenter : ListMovieContract.Presenter, ListMovieContract.AsyncTask {

    private var view: ListMovieContract.View? = null
    private var context: Context? = null
    private var listMovie: ArrayList<Movie>? = null
    private val movieDBConnection: MovieDBConnection = MovieDBConnection()

    override fun attachView(view: ListMovieContract.View) {
        this.view = view
        this.context = (view as ListMovieActivity).baseContext
    }

    override fun detachView() {
        this.view = null
        this.context = null
    }

    override fun loadData(orderBy: ListMovieOrderBy) {
        if(this.listMovie == null)
            executeAsyncTaskData(orderBy)
        else {
            if(view != null)
                view!!.fillList(this.listMovie!!)
        }
    }

    override fun reloadData(orderBy: ListMovieOrderBy) {
        executeAsyncTaskData(orderBy)
    }

    override fun updateList(listMovie: ArrayList<Movie>) {
        this.listMovie = listMovie
    }

    override fun getListMovie(): ArrayList<Movie> {
        return this.listMovie!!
    }

    override fun processStart() {
        view!!.showLoading()
    }

    override fun processFinish(movies: ArrayList<Movie>) {
        view!!.fillList(movies)
    }

    override fun getOrderBy(svdOb: Int): ListMovieOrderBy {
        var orderBy = ListMovieOrderBy.values()
                .forEachIndexed({ index: Int, listMovieOrderBy: ListMovieOrderBy ->
                    if (listMovieOrderBy.order == svdOb) return listMovieOrderBy
                }) as ListMovieOrderBy

        return orderBy
    }


    private fun executeAsyncTaskData(orderBy: ListMovieOrderBy) {
        if(this.context != null) {
            if(hasInternetConnection(this.context as Context)){
                this.listMovie = ArrayList()
                ListMovieAsyncTask(this).execute(orderBy)
            } else {
                showConnectionError(view as ListMovieActivity)
            }
        }
    }

    override fun getPicture(posterPath: String, movieImage: ImageView) {
        movieDBConnection.getMovieImage(this.context!!, movieImage, posterPath)
    }

}
