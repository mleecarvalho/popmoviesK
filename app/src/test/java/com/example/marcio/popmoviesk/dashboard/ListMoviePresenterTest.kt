package com.example.marcio.popmoviesk.dashboard

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.example.marcio.popmoviesk.dashboard.ListMovieOrderBy.RATING
import org.junit.Assert
import org.mockito.Mockito

/**
 * Created by marcio on 22/01/2018.
 */

class ListMoviePresenterTest{

    private lateinit var presenter: ListMoviePresenter
    private val ratingOrder: Int = RATING.order

    @Mock
    protected lateinit var view: ListMovieActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(ListMoviePresenter())
        presenter.attachView(this.view)
    }

    @Test
    fun getOrderByText(){
       Assert.assertEquals(ratingOrder, presenter.getOrderBy(ratingOrder))
    }


}