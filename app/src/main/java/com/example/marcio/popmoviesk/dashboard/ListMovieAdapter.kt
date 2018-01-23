package com.example.marcio.popmoviesk.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.ViewGroup
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.data.model.Movie

/**
 * Created by marcio on 18/01/2018.
 */
class ListMovieAdapter(private val view: ListMovieContract.View) : RecyclerView.Adapter<ListMovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListMovieViewHolder {
        val inflater = from(parent!!.context)
        val inflateView = inflater.inflate(R.layout.adapter_list_movie_item, parent, false)
        return ListMovieViewHolder(inflateView, view)
    }

    override fun getItemCount() = when {
        this.listMovie != null -> listMovie.size
        else -> 0
    }


    override fun onBindViewHolder(holder: ListMovieViewHolder?, position: Int) {
        holder!!.bind(listMovie[position])
    }

    fun setMovieList(listMovie: ArrayList<Movie>){
        this.listMovie = listMovie
        notifyDataSetChanged()
    }

}