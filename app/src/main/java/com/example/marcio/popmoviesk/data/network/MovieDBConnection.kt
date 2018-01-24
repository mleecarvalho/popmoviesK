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

    private val apiDBKey: String = "3502fee1eb0ff1815d316905b20662eb"
    private val movieDBEndpoint: String = "https://api.themoviedb.org/"
    private val picassoEndpoint: String = "http://image.tmdb.org/t/p/w185"
    private val popularityPath: String = "3/movie/popular"
    private val topRatedPath: String = "3/movie/top_rated"


    fun requestMovies(type: ListMovieOrderBy): ArrayList<Movie>?{
        return requestMoviesList(this.buildRequestUrl(getRequestPath(type))!!)
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

    fun cancelRequest(context: Context, imageView: ImageView?) {
        Picasso.with(context).cancelRequest(imageView)
    }

    private fun getPicassoURLImage(picture: String) : String {
        val imgUrl = StringBuilder()
        if(!picture.isEmpty()){
            imgUrl.append(picassoEndpoint)
                    .append(picture)

        }
        return imgUrl.toString()
    }

    private fun getRequestPath(type: ListMovieOrderBy) =
            when(type) {
                ListMovieOrderBy.POPULARITY -> popularityPath
                else -> topRatedPath
            }

    private fun buildRequestUrl(path: String): URL? {
        val builtUri = Uri.parse(movieDBEndpoint)
                .buildUpon()
                .path(path)
                .appendQueryParameter("api_key", apiDBKey)
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

    private fun parseJsonListMovie(stringRequest: String) =
            MovieProcessor.process(JSONObject(stringRequest).getJSONArray("results"))

    private fun getResponseFromHttpUrl(movieUrl: URL): String? {
        val urlConnection: HttpURLConnection = movieUrl.openConnection() as HttpURLConnection
        try {
            val input: InputStream = urlConnection.inputStream

            val scanner = Scanner(input)
            scanner.useDelimiter("\\A")

            return if(scanner.hasNext()) scanner.next() else null
        }finally {
            urlConnection.disconnect()
        }
    }

}
