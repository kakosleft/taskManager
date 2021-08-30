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
import com.waaadedev.taskmanager.adapters.TaskRecyclerCompletedAdapter
import com.waaadedev.taskmanager.data.Task
import kotlinx.android.synthetic.main.fragment_completed_tasks.view.*

class CompletedTasksFragment : Fragment(),
    TaskRecyclerCompletedAdapter.ComplitedItemOnClickListener {
    lateinit var recyclerViewCompletedAdapter: TaskRecyclerCompletedAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)

        view.completed_tasks_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewCompletedAdapter = TaskRecyclerCompletedAdapter(this@CompletedTasksFragment)
            adapter = recyclerViewCompletedAdapter
        }

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getAllTasksObservers().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            recyclerViewCompletedAdapter.setListData(ArrayList(it))
            recyclerViewCompletedAdapter.notifyDataSetChanged()
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
        viewModel.updateTask(task)
    }
}