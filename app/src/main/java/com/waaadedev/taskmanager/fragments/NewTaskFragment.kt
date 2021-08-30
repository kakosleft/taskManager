package com.waaadedev.taskmanager.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.waaadedev.taskmanager.MainActivity
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.fragments.dialogs.DatePickerFragment
import com.waaadedev.taskmanager.fragments.dialogs.TimePickerFragment
import com.waaadedev.taskmanager.util.DateConverter
import com.waaadedev.taskmanager.viewModel.DialogsDataViewModel
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_new_task.view.*
import java.util.*

class NewTaskFragment : Fragment() {

    lateinit var time: Calendar
    lateinit var date: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        blockOnBackPresed()
        val view = inflater.inflate(R.layout.fragment_new_task, container, false)
        view.button_back.setOnClickListener {
            goBack()
        }

        view.create_task.setOnClickListener {
            val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
            try {
                val taskTime = GregorianCalendar(
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH),
                    time.get(Calendar.HOUR_OF_DAY),
                    time.get(Calendar.MINUTE)
                )
                var newTask = Task(
                    description = view.task_title_edit_text.text.toString(),
                    date = taskTime.timeInMillis,
                    id = 0,
                    isDone = false
                )

                viewModel.insertTask(newTask)
                goBack()
            } catch (e: Exception) {
                val toast =
                    Toast.makeText(context, "You must select date and time", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                Log.i("__", e.toString())
            }
        }

        val model: DialogsDataViewModel =
            ViewModelProviders.of(requireActivity()).get(DialogsDataViewModel::class.java)
        model.getDate.observe(viewLifecycleOwner) {
            date = it
            view.date_button.text = DateConverter().getDate(date.timeInMillis)
        }
        model.getTime.observe(viewLifecycleOwner) {
            time = it
            view.time_button.text = DateConverter().getTime(time.timeInMillis)
        }
        view.time_button.setOnClickListener {
            TimePickerFragment().show(activity?.supportFragmentManager!!, "timePicker")
        }

        view.date_button.setOnClickListener {
            DatePickerFragment().show(activity?.supportFragmentManager!!, "datePicker")
        }
        return view
    }

    private fun blockOnBackPresed() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })
    }


    fun goBack() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }

}