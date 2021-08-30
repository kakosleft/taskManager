package com.waaadedev.taskmanager.fragments.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.waaadedev.taskmanager.viewModel.DialogsDataViewModel
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val model: DialogsDataViewModel = ViewModelProviders.of(requireActivity()).get(
            DialogsDataViewModel::class.java)
        val calendar = GregorianCalendar()
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.set(Calendar.MINUTE,minute)
        model.setTime(calendar)
    }
}
