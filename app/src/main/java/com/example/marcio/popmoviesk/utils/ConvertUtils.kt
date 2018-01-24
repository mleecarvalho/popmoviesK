package com.example.marcio.popmoviesk.utils

import java.text.SimpleDateFormat

/**
 * Created by marcio on 18/01/2018.
 */
object ConvertUtils{

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val yearFormat = SimpleDateFormat("yyyy")

    fun getYearAmericanDate(dateStr: String): String = this.format(dateStr, yearFormat)

    private fun format(dateStr: String, formatter: SimpleDateFormat) =
            dateStr.let{ dateFormat.parse(it) }
                    .let{ formatter.format(it) }
}