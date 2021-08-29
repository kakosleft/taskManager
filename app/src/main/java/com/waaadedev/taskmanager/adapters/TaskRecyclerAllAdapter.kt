package com.waaadedev.taskmanager.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.waaadedev.taskmanager.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.data.TasksParrent
import com.waaadedev.taskmanager.fragments.AllTasksFragment
import com.waaadedev.taskmanager.util.DateConverter

class TaskRecyclerAllAdapter(
    private val activity: FragmentActivity?) :
    RecyclerView.Adapter<TaskRecyclerAllAdapter.ViewHolder>(),
    ItemsGroupRecyclerViewAdapter.RowClickListener {

    var items = ArrayList<TasksParrent>()

    fun setListData(data: ArrayList<TasksParrent>) {
        Log.i("/////////////", "$data")
        this.items = data
    }


    private lateinit var recyclerView: RecyclerView
    lateinit var itemsGroupRecyclerViewAdapter: ItemsGroupRecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskRecyclerAllAdapter.ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        viewModel = ViewModelProviders.of(activity!!).get(MainActivityViewModel::class.java)
        itemsGroupRecyclerViewAdapter = ItemsGroupRecyclerViewAdapter(this@TaskRecyclerAllAdapter)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items.size != 0) {
            recyclerView = holder.itemView.findViewById(R.id.items_recycler_view)
            recyclerView.apply {
                layoutManager = GridLayoutManager(activity, 1)
                adapter = itemsGroupRecyclerViewAdapter
            }
            itemsGroupRecyclerViewAdapter.setListData(items[position].tasks)
            itemsGroupRecyclerViewAdapter.notifyDataSetChanged()

            if (position == 1) {
                holder.itemView.setBackgroundResource(R.drawable.items_background)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onItemClickListener(task: Task) {
        val dialog = MaterialDialog(activity!!)
            .noAutoDismiss()
            .customView(R.layout.dialog_delete_task)

        val buttonYes = dialog.findViewById<Button>(R.id.delete_task_dialog_yes)
        val buttonNo = dialog.findViewById<Button>(R.id.delete_task_dialog_no)

        buttonNo.setOnClickListener { dialog.cancel() }
        buttonYes.setOnClickListener {
            viewModel.deleteTask(task)
            dialog.cancel()
            itemsGroupRecyclerViewAdapter.notifyDataSetChanged()
        }
        dialog.show()
    }

    override fun onTaskCompliteListener(task: Task) {
        task.isDone = !task.isDone
        Log.i("----------", task.toString())
        viewModel.updateTask(task)
        itemsGroupRecyclerViewAdapter.notifyDataSetChanged()
    }
}
