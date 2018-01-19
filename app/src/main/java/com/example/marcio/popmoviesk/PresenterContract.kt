package com.example.marcio.popmoviesk

/**
 * Created by marcio on 18/01/2018.
 */
interface PresenterContract<V> {
    fun attachView(view: V)
    fun detachView()
}