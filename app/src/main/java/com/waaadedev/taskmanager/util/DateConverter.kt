package com.waaadedev.taskmanager.util

import java.text.SimpleDateFormat
import java.util.*

class DateConverter() {

    fun getTime(time: Long): String{
        val formater = SimpleDateFormat("HH:mm")
        return formater.format(Date(time))
    }

    fun getDate(date: Long): String{
        val formater = SimpleDateFormat("dd:MM:yyyy")
        return formater.format(Date(date))
    }

    fun getCurrentDay(): String{
        val calendar = Calendar.getInstance()
        val date = calendar.time
        return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
    }

    fun getDate(): String{
        val calendar = Calendar.getInstance()
        val date = calendar.time
        return SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(date.time)
    }

}