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

class TaskRecyclerAllAdapter(private val listener: RowClickListener) :
    RecyclerView.Adapter<TaskRecyclerAllAdapter.ViewHolder>() {

    var items = ArrayList<Task>()

    fun setListData(data: ArrayList<Task>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskRecyclerAllAdapter.ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: TaskRecyclerAllAdapter.ViewHolder, position: Int) {
//        if (position == items.size - 1){
//            holder.itemView.setPadding(30,30,30,250)
//        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class ViewHolder(itemView: View, val listener: RowClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_task_title)
        val time = itemView.findViewById<TextView>(R.id.item_task_time)
        val date = itemView.findViewById<TextView>(R.id.item_task_date)
        val isDone = itemView.findViewById<CheckBox>(R.id.item_task_check_box)

        fun bind(task: Task) {
            title.text = task.description
            time.text = DateConverter().getTime(task.date)
            date.text = DateConverter().getDate(task.date)
            isDone.isChecked = task.isDone

            itemView.setOnLongClickListener {
                listener.onItemClickListener(task)
                true
            }

            itemView.setOnClickListener{
                listener.onTaskCompliteListener(task)
            }

        }
    }

    interface RowClickListener {
        fun onItemClickListener(task: Task)
        fun onTaskCompliteListener(task: Task)
    }
}
