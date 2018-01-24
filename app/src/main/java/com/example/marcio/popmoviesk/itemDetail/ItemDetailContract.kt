package com.example.marcio.popmoviesk.itemDetail

import android.widget.ImageView
import com.example.marcio.popmoviesk.PresenterContract
import android.support.v7.graphics.Palette

/**
 * Created by marcio on 19/01/2018.
 */

interface ItemDetailContract {

    interface View{
        fun setCollapsePallete(pallete: Palette)
    }

    interface Presenter: PresenterContract<View>{
        fun loadMovieImage(imageView: ImageView, imagePath: String)
        fun loadCollapseImage( imageView: ImageView, imagePath: String)
        fun averageRateResults(averageVote : Double): Float
    }

}