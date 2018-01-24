package com.example.marcio.popmoviesk.data

import com.example.marcio.popmoviesk.dashboard.ListMovieOrderBy
import com.example.marcio.popmoviesk.data.network.MovieDBConnection
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by marcio on 24/01/2018.
 */

@RunWith(RobolectricTestRunner::class)
class MovieDBConnectionTest {

    private val dbConnection = MovieDBConnection()

    @Before
    fun setUp() {

    }

    @Test
    fun buildResquestUrlPopularTest(){
      val popularList = dbConnection.requestMovies(ListMovieOrderBy.POPULARITY)
      Assert.assertNotNull(popularList)
    }

    @Test
    fun buildResquestUrlRatingTest(){
        val popularList = dbConnection.requestMovies(ListMovieOrderBy.RATING)
        Assert.assertNotNull(popularList)
    }
}