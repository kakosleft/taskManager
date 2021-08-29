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
}