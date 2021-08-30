package com.waaadedev.taskmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.util.DateConverter
import kotlinx.android.synthetic.main.item_task.view.*

class TaskRecyclerCompletedAdapter(private val listener: ComplitedItemOnClickListener) :
    RecyclerView.Adapter<TaskRecyclerCompletedAdapter.ViewHolder>() {

    var items = ArrayList<Task>()

    fun setListData(data: ArrayList<Task>) {
        val newList = ArrayList<Task>()
        data.forEach {
            if (it.isDone) {
                newList.add(it)
            }
        }
        this.items = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskRecyclerCompletedAdapter.ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
    inner class ViewHolder(itemView: View, val listener: ComplitedItemOnClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            itemView.item_task_title.text = task.description
            itemView.item_task_time.text = DateConverter().getTime(task.date)
            itemView.item_task_date.text = DateConverter().getDate(task.date)
            itemView.item_task_check_box.isChecked = task.isDone

            itemView.setOnLongClickListener {
                listener.onItemClickListener(task)
                true
            }

            itemView.setOnClickListener {
                listener.onTaskCompliteListener(task)
            }
        }
    }

    interface ComplitedItemOnClickListener {
        fun onItemClickListener(task: Task)
        fun onTaskCompliteListener(task: Task)
    }

}
