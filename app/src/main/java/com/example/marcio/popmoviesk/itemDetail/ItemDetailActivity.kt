package com.example.marcio.popmoviesk.itemDetail

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.utils.ConvertUtils.getYearAmericanDate
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * Created by marcio on 19/01/2018.
 */

class ItemDetailActivity : AppCompatActivity(), ItemDetailContract.View {

    private lateinit var presenter: ItemDetailContract.Presenter
    private lateinit var movie: Movie
    private lateinit var toplbar: Toolbar
    private lateinit var collapseToolbarLayout: CollapsingToolbarLayout
    private lateinit var ratingBar: RatingBar
    private lateinit var synopse: TextView
    private lateinit var collapseImage: ImageView
    private lateinit var movieImage: ImageView
    private lateinit var releaseDate: TextView
    private lateinit var originalTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        this.movie = intent.getParcelableExtra<Movie>(LISTKEY)
        setPresenter()
        setFields()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setCollapsePallete(pallete: Palette) {
        val primaryDarkColor = resources.getColor(R.color.colorPrimaryDark)
        val primaryColor = resources.getColor(R.color.colorPrimary)
        collapseToolbarLayout.setContentScrimColor(pallete.getDarkMutedColor(primaryColor))
        collapseToolbarLayout.setStatusBarScrimColor(pallete.getDarkMutedColor(primaryDarkColor))
    }

    private fun setFields() {
        collapseImage = collapse_image
        movieImage = movie_item_image
        presenter.loadMovieImage(movieImage, movie.posterPath)
        presenter.loadCollapseImage(collapseImage, movie.backdropPath)

        this.toplbar = toolbar
        setSupportActionBar(this.toplbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(R.string.movie_details_title)

        collapseToolbarLayout = collapse_toolbar
        collapseToolbarLayout.title = movie.title

        originalTitle = original_title_text
        originalTitle.text = movie.originalTitle

        ratingBar = rating_bar
        ratingBar.rating = presenter.averageRateResults(movie.voteAverage)

        releaseDate = release_date_text
        releaseDate.text = getYearAmericanDate(movie.releaseDate)

        synopse = synopse_text
        synopse.text = movie.overview

    }

    private fun setPresenter() {
        this.presenter = ItemDetailPresenter()
        this.presenter.attachView(this)
    }

}