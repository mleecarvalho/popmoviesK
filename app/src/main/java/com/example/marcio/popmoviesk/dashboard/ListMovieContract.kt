package com.example.marcio.popmoviesk.dashboard

import com.example.marcio.popmoviesk.PresenterContract
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection

/**
 * Created by marcio on 18/01/2018.
 */
interface ListMovieContract{

    interface View{
        fun fillList(listMovie: ArrayList<Movie>)
        fun showLoading()
        fun openItem(movie: Movie)
    }

    interface Presenter : PresenterContract<View>{
        fun loadData(orderBy: ListMovieOrderBy)
        fun reloadData(orderBy: ListMovieOrderBy)
        fun updateList(listMovie: ArrayList<Movie>)
        fun getListMovie(): ArrayList<Movie>
        fun getOrderBy(svdOb: Int): ListMovieOrderBy
        fun getMovieDBConnection(): MovieDBConnection
    }

    interface AsyncTask{
        fun processStart()
        fun processFinish(movie: ArrayList<Movie>)
    }

}