package com.example.marcio.popmoviesk.dashboard

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.R.id.movie_image
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.network.MovieDBConnection
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_list_movie_item.view.*

/**
 * Created by marcio on 18/01/2018.
 */
class ListMovieViewHolder(view: View, mView: ListMovieContract.View, movieConnection: MovieDBConnection)
    : RecyclerView.ViewHolder(view), View.OnClickListener{

    private val view = mView
    private lateinit var movie: Movie
    private val movieTitle: TextView = view.movie_title
    private val movieImage: ImageView = view.movie_image
    private val movieConnection = movieConnection


    fun bind(movie: Movie){
        this.movie = movie
        this.movieTitle.text = movie.title
        movieImage.setOnClickListener(this)
        getPicture()
    }

    override fun onClick(v: View?) {
        this.view.openItem(movie)
    }

    private fun getPicture() {
        Picasso.with(itemView.context)
                .load(movieConnection.getPicassoURLImage(movie.posterPath))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(movieImage)
    }


}