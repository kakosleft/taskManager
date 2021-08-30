package com.waaadedev.taskmanager.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.adapters.TaskRecyclerAllAdapter
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.data.TasksParrent
import com.waaadedev.taskmanager.util.DateConverter

class AllTasksFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAllAdapter: TaskRecyclerAllAdapter
    lateinit var viewModel: MainActivityViewModel

    lateinit var dayOfWeek: TextView
    lateinit var dateTime: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_all_tasks, container, false)

        dayOfWeek = view.findViewById(R.id.day_of_week)
        dateTime = view.findViewById(R.id.date_main)

        dayOfWeek.text = DateConverter().getCurrentDay()
        dateTime.text = DateConverter().getDate()

        recyclerView = view.findViewById(R.id.all_tasks_recycler_view)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        recyclerView.apply {
            layoutManager = GridLayoutManager(activity,1)
            recyclerViewAllAdapter = TaskRecyclerAllAdapter(requireActivity(),viewModel)
            adapter = recyclerViewAllAdapter
        }

        viewModel.getAllTasksObservers().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            recyclerViewAllAdapter.setListData(preparationData(it))
            recyclerViewAllAdapter.notifyDataSetChanged()
        })

        return view
    }

    private fun preparationData(it: List<Task>?): ArrayList<TasksParrent> {
        val doneTasks = ArrayList<Task>()
        val currentTasks = ArrayList<Task>()
        val parrent = ArrayList<TasksParrent>()
        it?.forEach {
            if (it.isDone){
                doneTasks.add(it)
            }else{
                currentTasks.add(it)
            }
        }
        parrent.add(TasksParrent(doneTasks))
        parrent.add(TasksParrent(currentTasks))
        return parrent
    }

}