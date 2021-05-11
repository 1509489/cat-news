package com.sky.catnews.common

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun getFormattedDate(dateString: String): String {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val dateTimeFormatter = SimpleDateFormat(pattern, Locale.ENGLISH)
        val date = dateTimeFormatter.parse(dateString)

        return PrettyTime().format(date)
    }
}
