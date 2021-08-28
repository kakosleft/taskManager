package com.waaadedev.taskmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waaadedev.taskmanager.R

class TaskRecyclerAdapter: RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.item_task_title)
        val time: TextView = itemView.findViewById(R.id.item_task_time)
        val date: TextView = itemView.findViewById(R.id.item_task_date)
        val checkBox: CheckBox = itemView.findViewById(R.id.item_task_check_box)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskRecyclerAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }


}