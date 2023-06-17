package com.yasser.photoweather.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



fun String.convertToDate(
    dateTimeFormat: String = "yyyy-MM-dd",
    languageCode:String="en",
    timeZone: String = "UTC"
): Date? {
    val df = SimpleDateFormat(dateTimeFormat, Locale(languageCode))
    df.timeZone = TimeZone.getTimeZone(timeZone)
    return try {
        df.parse(this)
    } catch (e: ParseException) {
        null
    }

}


fun Long.convertToDate(
): Date? {
    return try {
        Date(this)
    } catch (e: ParseException) {
        null
    }

}

fun Date.convertToUiDate(): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale("en"))
    return format.format(this)
}
