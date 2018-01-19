package com.example.marcio.popmoviesk.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by marcio on 18/01/2018.
 */
class ConvertUtils(){

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun formatToGregorianDate(dateStr: String) : String{
        val convertFormat = SimpleDateFormat("dd/MM/yyyy")
        var date: Date? = null

        try{
            date = dateFormat.parse(dateStr)
        }catch (e: ParseException){
            e.printStackTrace()
        }
        return convertFormat.format(date)
    }

    fun getYearAmericanDate(dateStr: String) : String{
        val yearFormat = SimpleDateFormat("yyyy")
        var date: Date? = null

        try{
            date = dateFormat.parse(dateStr)
        }catch (e: ParseException){
            e.printStackTrace()
        }
        return yearFormat.format(date)

    }

}