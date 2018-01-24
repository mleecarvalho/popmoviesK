package com.example.marcio.popmoviesk.itemDetail

import junit.framework.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by marcio on 24/01/2018.
 */
class ItemDetailPresenterTest {

    private lateinit var presenter: ItemDetailContract.Presenter

    @Before
    fun setUp() {
        presenter = ItemDetailPresenter()
    }

    @Test
    fun averageResultsTest(){
        Assert.assertEquals(4.5F, presenter.averageRateResults(9.0))
    }

}