package com.example.marcio.popmoviesk.dashboard

import org.junit.Before
import org.junit.Test
import com.example.marcio.popmoviesk.dashboard.ListMovieOrderBy.RATING
import com.example.marcio.popmoviesk.data.model.Movie
import com.example.marcio.popmoviesk.data.model.MovieProcessor
import org.json.JSONObject
import org.junit.Assert
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.InputStream

/**
 * Created by marcio on 22/01/2018.
 */
@RunWith(RobolectricTestRunner::class)
class ListMoviePresenterTest{

    private lateinit var view: ListMovieActivity

    private lateinit var presenter: ListMoviePresenter
    private val popularMoviesMockFileStream = javaClass.classLoader
            .getResourceAsStream("popularMovies.json")
    private val ratingMoviesMockFileStream = javaClass.classLoader
            .getResourceAsStream("ratingMovies.json")

    @Before
    fun setUp() {
        presenter = ListMoviePresenter()
    }

    @Test
    fun getOrderByText(){
       Assert.assertEquals(RATING, presenter.getOrderBy(RATING.order))
    }

    @Test
    fun updateDataTest(){

        var firstList: ArrayList<Movie>?

        // carrega os dados
        presenter.updateList(getMockMoviesResult(popularMoviesMockFileStream))

        firstList = presenter.getListMovie()

        // checa se lista de filmes não está nula
        Assert.assertNotNull(firstList)

        // solicita atualização dos dados em outra ordem
        presenter.updateList(getMockMoviesResult(ratingMoviesMockFileStream))

        val lastList: ArrayList<Movie> = presenter.getListMovie()

        // checa se nova listagem é diferente da anterior
        Assert.assertNotEquals(firstList, lastList)

    }

    private fun getMockMoviesResult(inputStream: InputStream) =
            MovieProcessor
                    .process(
                            JSONObject(inputStream.bufferedReader().use { it.readText() }
                    ).getJSONArray("results"))


}