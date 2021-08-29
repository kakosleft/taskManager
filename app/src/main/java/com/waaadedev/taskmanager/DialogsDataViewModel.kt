package com.waaadedev.taskmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class DialogsDataViewModel: ViewModel() {
    private val date = MutableLiveData<Calendar>()
    private val time = MutableLiveData<Calendar>()

    val getDate: LiveData<Calendar> get() = date

    fun setDate(calendar: Calendar) {
        date.value = calendar
    }
    val getTime: LiveData<Calendar> get() = time

    fun setTime(calendar: Calendar) {
        time.value = calendar
    }
}