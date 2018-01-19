package com.example.marcio.popmoviesk.dashboard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.dashboard.ListMovieContract.View
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.itemDetail.ItemDetailActivity
import com.example.marcio.popmoviesk.itemDetail.LISTKEY
import com.example.marcio.popmoviesk.utils.NetConnection.hasInternetConnection
import com.example.marcio.popmoviesk.utils.NetConnection.showConnectionError
import kotlinx.android.synthetic.main.content_list_movie.*

class ListMovieActivity : AppCompatActivity(), View {

    private lateinit var presenter: ListMovieContract.Presenter
    private var orderBy: ListMovieOrderBy? = null
    private val ORDERBY_KEY = "orderBy"
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var listMovieAdapter : ListMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        setPresenter()
        setFields()
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData(orderBy!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun setFields() {
        this.progressBar = list_move_progressbar
        this.recyclerView = list_movie_recycleview
        if(this.orderBy == null)
            this.orderBy = ListMovieOrderBy.POPULARITY
    }

    private fun setAdapter() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        this.recyclerView.layoutManager =  layoutManager
        this.recyclerView.setHasFixedSize(true)
        this.listMovieAdapter = ListMovieAdapter(this)
        recyclerView.adapter = this.listMovieAdapter
    }

    private fun setPresenter() {
        this.presenter = ListMoviePresenter()
        this.presenter.attachView(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt(ORDERBY_KEY, orderBy!!.order)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState != null && savedInstanceState.containsKey(ORDERBY_KEY))
            if(presenter != null)
                this.orderBy = presenter.getOrderBy(savedInstanceState.getInt(ORDERBY_KEY))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.ordey_by_popularity -> {
                if (setOrderBy(ListMovieOrderBy.POPULARITY))
                    reloadList()
                return true
            }
            R.id.order_by_rating -> {
                if (setOrderBy(ListMovieOrderBy.RATING))
                    reloadList()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun fillList(listMovie: ArrayList<Movie>) {
        hideLoading()
        presenter.updateList(listMovie)
        listMovieAdapter.setMovieList(listMovie)
    }

    private fun hideLoading() {
        this.progressBar.visibility = GONE
    }

    override fun showLoading() {
        progressBar.visibility = VISIBLE
    }

    override fun openItem(movie: Movie) {
        val intent = Intent(this, ItemDetailActivity::class.java)
        intent.putExtra(LISTKEY, movie)
        startActivity(intent)
    }

    override fun requestPicture(posterPath: String, movieImage: ImageView) {
        presenter.getPicture(posterPath, movieImage)
    }

    private fun reloadList() {
        if(hasInternetConnection(this)){
            setAdapter()
            presenter.reloadData(orderBy!!)
        }else{
            showConnectionError(this)
        }
    }

    private fun setOrderBy(order: ListMovieOrderBy): Boolean {
        val mustSet = !(order == this.orderBy)
        if(mustSet)
            this.orderBy = order
        return mustSet
    }


}
