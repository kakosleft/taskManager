package com.waaadedev.taskmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.data.TasksParrent

class TaskRecyclerAllAdapter(
    private val activity: FragmentActivity,
    private val viewModel: MainActivityViewModel
) :
    RecyclerView.Adapter<TaskRecyclerAllAdapter.ViewHolder>(),
    ItemsGroupRecyclerViewAdapter.RowClickListener {

    private lateinit var recyclerView: RecyclerView
    lateinit var itemsGroupRecyclerViewAdapter: ItemsGroupRecyclerViewAdapter
    var items = ArrayList<TasksParrent>()

    fun setListData(data: ArrayList<TasksParrent>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskRecyclerAllAdapter.ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
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

            if (position == 0) {
                holder.itemView.setBackgroundResource(R.drawable.items_background_is_done)
            }else{
                holder.itemView.setBackgroundResource(R.drawable.items_background_is_current)
                val param = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
                val newParam = param
                newParam.setMargins(param.leftMargin,param.topMargin,param.rightMargin, 400)
                holder.itemView.layoutParams = newParam
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

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
        viewModel.updateTask(task)
        itemsGroupRecyclerViewAdapter.notifyDataSetChanged()
    }

}
