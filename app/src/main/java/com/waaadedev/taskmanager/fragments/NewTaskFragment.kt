package com.waaadedev.taskmanager.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.waaadedev.taskmanager.viewModel.DialogsDataViewModel
import com.waaadedev.taskmanager.MainActivity
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.fragments.dialogs.DatePickerFragment
import com.waaadedev.taskmanager.fragments.dialogs.TimePickerFragment
import java.util.*


class NewTaskFragment : Fragment() {

    lateinit var createTask: Button
    lateinit var back: ImageButton
    lateinit var timeButton: Button
    lateinit var dateButton: Button
    lateinit var title: EditText


    lateinit var time: Calendar
    lateinit var date: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_task, container, false)
        title = view.findViewById(R.id.task_title_edit_text)
        timeButton = view.findViewById(R.id.time_button)
        back = view.findViewById(R.id.button_back)
        dateButton = view.findViewById(R.id.date_button)
        createTask = view.findViewById(R.id.create_task)

        back.setOnClickListener{
            goBack()
        }

        createTask.setOnClickListener {
            val viewModel= ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
            try {
                val taskTime = GregorianCalendar(
                    date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH),
                    time.get(Calendar.HOUR_OF_DAY),
                    time.get(Calendar.MINUTE)
                )
                var newTask= Task(
                    description = title.text.toString(),
                    date = taskTime.timeInMillis,
                    text = "234234",
                    id = 0,
                    isDone = false
                )

                viewModel.insertTask(newTask)
                goBack()
            }catch (e: Exception){
                Log.i("__",e.toString())
            }
        }

        val model: DialogsDataViewModel =
            ViewModelProviders.of(requireActivity()).get(DialogsDataViewModel::class.java)
        model.getDate.observe(viewLifecycleOwner) {
            date = it
        }
        model.getTime.observe(viewLifecycleOwner) {
            time = it
        }
        timeButton.setOnClickListener {
            TimePickerFragment().show(activity?.supportFragmentManager!!, "timePicker")
        }

        dateButton.setOnClickListener {
            DatePickerFragment().show(activity?.supportFragmentManager!!, "datePicker")
        }
        return view
    }

    fun goBack(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }

}