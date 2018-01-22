package com.example.marcio.popmoviesk.itemDetail

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.graphics.Palette
import android.util.Log
import android.widget.ImageView
import com.example.marcio.popmoviesk.data.network.MovieDBConnection
import com.squareup.picasso.Callback

/**
 * Created by marcio on 19/01/2018.
 */
class ItemDetailPresenter : ItemDetailContract.Presenter, Callback {

    private var view: ItemDetailContract.View? = null
    private var context: Context? = null
    private var dbConnection: MovieDBConnection = MovieDBConnection()
    private var collapseImageView: ImageView? = null

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
        this.collapseImageView = imageView
        dbConnection.getCollapseImage(this.context!!, imageView, imagePath, this)
    }

    override fun averageRateResults(averageVote: Double): Float {
        return averageVote.toFloat() / 2
    }

    override fun onSuccess() {
        if (collapseImageView != null) {
            val bitmap = (collapseImageView!!.drawable as BitmapDrawable).bitmap
            Palette.from(bitmap).generate { palette -> view!!.setCollapsePallete(palette) }
        }
    }

    override fun onError() {
        Log.e("imageCallback", "unknown error")
    }


}