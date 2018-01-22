package com.example.marcio.popmoviesk.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by marcio on 18/01/2018.
 */
object ConvertUtils{

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val gregorianFormat = SimpleDateFormat("dd/MM/yyyy")
    private val yearFormat = SimpleDateFormat("yyyy")

    fun formatToGregorianDate(dateStr: String) = format(dateStr, gregorianFormat)
    fun getYearAmericanDate(dateStr: String)  = format(dateStr, yearFormat)

   private fun format(dateStr: String, formatter: SimpleDateFormat) =
            dateStr.let { dateFormat.parse(it) }
                    .let { formatter.format(it) }
}