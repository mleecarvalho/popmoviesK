package com.example.marcio.popmoviesk.itemDetail

import android.content.Context
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.example.marcio.popmoviesk.data.network.MovieDBConnection
import com.example.marcio.popmoviesk.data.network.MovieDBConnectionListener
import com.squareup.picasso.Picasso

/**
 * Created by marcio on 19/01/2018.
 */
class ItemDetailPresenter: ItemDetailContract.Presenter, MovieDBConnectionListener {

    private var view: ItemDetailContract.View? = null
    private var context: Context? = null
    private val dbConnection: MovieDBConnection = MovieDBConnection()

    override fun attachView(view: ItemDetailContract.View) {
        this.view = view
        this.context = (view as ItemDetailActivity).baseContext

    }

    override fun detachView() {
        this.view = null
        this.context = null
    }

    override fun loadMovieImage(imageView: ImageView, imagePath: String) {
        dbConnection.getMovieImage(this.context!!, imageView, imagePath)
    }

    override fun loadCollapseImage(imageView: ImageView, imagePath: String) {
        dbConnection.getCollapseImage(this.context!!, imageView, imagePath, this)
    }

    override fun imageCallback(pallete: Palette) {
        this.view!!.setCollapsePallete(pallete)
    }

}