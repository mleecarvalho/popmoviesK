package com.example.marcio.popmoviesk.dashboard

/**
 * Created by marcio on 18/01/2018.
 */

enum class ListMovieOrderBy(order: Int){
    POPULARITY(1),
    RATING(2);

    var order: Int = order

}