package com.example.marcio.popmoviesk.data.model

import org.json.JSONArray
import org.json.JSONException

/**
 * Created by marcio on 18/01/2018.
 */
object MovieProcessor {

    fun process(results: JSONArray): ArrayList<Movie>{
        val movies = ArrayList<Movie>()
        try {
            if (results.length() > 0){
                for(i in 0..(results.length() - 1)){
                    movies.add(Movie(results.getJSONObject(i)))
                }

            }
        }catch (e: JSONException){
            e.printStackTrace()

        }
        return movies
    }
}