package com.example.marcio.popmoviesk.data.network

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.example.marcio.popmoviesk.R
import com.example.marcio.popmoviesk.dashboard.ListMovieOrderBy
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.model.MovieProcessor
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by marcio on 18/01/2018.
 */


class MovieDBConnection {

    private val API_DBKEY: String = "3502fee1eb0ff1815d316905b20662eb"
    private val MOVIEDB_ENDPOINT: String = "https://api.themoviedb.org/"
    private val PICASSO_ENDPOINT: String = "http://image.tmdb.org/t/p/w185"
    private val POPULARITY_PATH: String = "3/movie/popular"
    private val TOP_RATED_PATH: String = "3/movie/top_rated"


    fun requestMovies(type: ListMovieOrderBy): ArrayList<Movie>?{
        return requestMoviesList(this!!.buildRequestUrl(getRequestPath(type))!!)
    }

    fun getMovieImage(context: Context, imageView: ImageView, imagePath: String){
        Picasso.with(context)
                .load(this.getPicassoURLImage(imagePath))
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .into(imageView)
    }

    fun getCollapseImage(context: Context, imageView: ImageView, imagePath: String, listener : Callback){
       Picasso.with(context)
                .load(this.getPicassoURLImage(imagePath))
                .into(imageView, listener)
    }


    private fun getPicassoURLImage(picture: String) : String {
        var imgUrl = StringBuilder()
        if(!picture.isEmpty()){
            imgUrl.append(PICASSO_ENDPOINT)
                    .append(picture)

        }
        return imgUrl.toString()
    }

    private fun getRequestPath(type: ListMovieOrderBy): String {
        return if(type == ListMovieOrderBy.POPULARITY) POPULARITY_PATH else TOP_RATED_PATH
    }

    private fun buildRequestUrl(path: String): URL? {
        val builtUri = Uri.parse(MOVIEDB_ENDPOINT)
                .buildUpon()
                .path(path)
                .appendQueryParameter("api_key", API_DBKEY)
                .build()
        var requestURL: URL? = null
        try {
            requestURL = URL(builtUri.toString())
            Log.i("URL data", requestURL.toString())
        }catch (e: MalformedURLException){

        }
        return requestURL
    }

    private fun requestMoviesList(movieUrl: URL) : ArrayList<Movie>?{
        var request: ArrayList<Movie>? = null
        try {
            val stringRequest = getResponseFromHttpUrl(movieUrl)
            Log.e("Request List", stringRequest)
            request = parseJsonListMovie(stringRequest!!)
        } catch (e: Exception){

        }
        return request
    }

    private fun parseJsonListMovie(stringRequest: String): ArrayList<Movie>? {
        val results = JSONObject(stringRequest).getJSONArray("results")
        return MovieProcessor.process(results)
    }

    private fun getResponseFromHttpUrl(movieUrl: URL): String? {
        var urlConnection: HttpURLConnection = movieUrl.openConnection() as HttpURLConnection
        try {
            val input: InputStream = urlConnection.inputStream;

            val scanner = Scanner(input)
            scanner.useDelimiter("\\A")

            return if(scanner.hasNext()) scanner.next() else null
        }finally {
            urlConnection.disconnect()
        }
    }


}
