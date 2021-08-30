package com.waaadedev.taskmanager.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.adapters.TaskRecyclerCurrentAdapter
import com.waaadedev.taskmanager.data.Task

class CurrentTasksFragment : Fragment(), TaskRecyclerCurrentAdapter.CurrentItemOnClickListener {

    private lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewCurrentAdapter: TaskRecyclerCurrentAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_current_tasks, container, false)

        recyclerView = view.findViewById(R.id.current_tasks_recycler_view)

        recyclerView.apply {
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

    override fun onItemClickListener(task: Task) {
        val dialog = MaterialDialog(requireActivity())
            .noAutoDismiss()
            .customView(R.layout.dialog_delete_task)

        val buttonYes = dialog.findViewById<Button>(R.id.delete_task_dialog_yes)
        val buttonNo = dialog.findViewById<Button>(R.id.delete_task_dialog_no)

        buttonNo.setOnClickListener { dialog.cancel() }
        buttonYes.setOnClickListener {
            viewModel.deleteTask(task)
            dialog.cancel()
        }
        dialog.show()
    }

    override fun onTaskCompliteListener(task: Task) {
        task.isDone = !task.isDone
        Log.i("----------", task.toString())
        viewModel.updateTask(task)
    }
}