package com.waaadedev.taskmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.adapters.TaskRecyclerCurrentAdapter
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.util.ItemsOnClick
import kotlinx.android.synthetic.main.fragment_current_tasks.view.*

class CurrentTasksFragment : Fragment(), TaskRecyclerCurrentAdapter.CurrentItemOnClickListener {

    lateinit var recyclerViewCurrentAdapter: TaskRecyclerCurrentAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_current_tasks, container, false)

        view.current_tasks_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewCurrentAdapter = TaskRecyclerCurrentAdapter(this@CurrentTasksFragment)
            adapter = recyclerViewCurrentAdapter
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllTasksObservers().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            recyclerViewCurrentAdapter.setListData(ArrayList(it))
            recyclerViewCurrentAdapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onItemLongClickListener(task: Task) {
        ItemsOnClick().onItemLongClick(task, requireActivity(), viewModel)
    }

    override fun onTaskCompliteListener(task: Task) {
        ItemsOnClick().onTaskComplite(task, viewModel)
    }
}