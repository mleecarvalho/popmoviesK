package com.example.marcio.popmoviesk.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection

/**
 * Created by marcio on 18/01/2018.
 */
class ListMovieAdapter(private val view: ListMovieContract.View, movieDBConnection: MovieDBConnection) : RecyclerView.Adapter<ListMovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    private val movieDBConnection = movieDBConnection;


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListMovieViewHolder {
        val inflater = from(parent!!.context)
        val inflateView = inflater.inflate(R.layout.adapter_list_movie_item, parent, false)
        return ListMovieViewHolder(inflateView, view, movieDBConnection)
    }

    override fun getItemCount(): Int {
        return if(this.listMovie != null) listMovie.size else 0
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder?, position: Int) {
        holder!!.bind(listMovie.get(position))
    }

    fun setMovieList(listMovie: ArrayList<Movie>){
        this.listMovie = listMovie
        notifyDataSetChanged()
    }

}