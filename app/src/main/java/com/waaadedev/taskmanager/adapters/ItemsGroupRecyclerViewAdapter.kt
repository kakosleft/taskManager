package com.waaadedev.taskmanager.adapters

import android.graphics.Color
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

class ItemsGroupRecyclerViewAdapter(private val listener: RowClickListener) :
    RecyclerView.Adapter<ItemsGroupRecyclerViewAdapter.ViewHolder>() {

    var items = ArrayList<Task>()
    fun setListData(data: ArrayList<Task>) {
        this.items = data
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int):
            ItemsGroupRecyclerViewAdapter.ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ItemsGroupRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View, val listener: RowClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(task: Task) {
            itemView.item_task_title.text = task.description
            itemView.item_task_time.text = DateConverter().getTime(task.date)
            itemView.item_task_date.text = DateConverter().getDate(task.date)
            itemView.item_task_check_box.isChecked = task.isDone

            itemView.setOnLongClickListener {
                listener.onItemLongClickListener(task)
                true
            }
            setColor(task)
            itemView.setOnClickListener {
                listener.onTaskCompliteListener(task)
            }
        }

        fun setColor(task: Task) {
            if (task.isDone) {
                itemView.item_task_title.setTextColor(Color.parseColor("#ffffff"))
                itemView.item_task_time.setTextColor(Color.parseColor("#ffffff"))
                itemView.item_task_date.setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }

    interface RowClickListener {
        fun onItemLongClickListener(task: Task)
        fun onTaskCompliteListener(task: Task)
    }
}