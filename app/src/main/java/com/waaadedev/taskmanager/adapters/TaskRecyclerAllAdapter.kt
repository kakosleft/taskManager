package com.waaadedev.taskmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.data.TasksParrent
import com.waaadedev.taskmanager.util.ItemsOnClick
import kotlinx.android.synthetic.main.item_group.view.*

class TaskRecyclerAllAdapter(
    private val activity: FragmentActivity, private val viewModel: MainActivityViewModel):
    RecyclerView.Adapter<TaskRecyclerAllAdapter.ViewHolder>(),
    ItemsGroupRecyclerViewAdapter.RowClickListener {

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
            holder.itemView.items_recycler_view.apply {
                layoutManager = GridLayoutManager(activity, 1)
                adapter = itemsGroupRecyclerViewAdapter
            }
            itemsGroupRecyclerViewAdapter.setListData(items[position].tasks)

            if (position == 0) {
                holder.itemView.setBackgroundResource(R.drawable.group_background_is_done)
            }else{
                holder.itemView.setBackgroundResource(R.drawable.group_background_is_current)
                val param = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
                val newParam = param
                newParam.setMargins(param.leftMargin,param.topMargin,param.rightMargin, 400)
                holder.itemView.layoutParams = newParam
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onItemLongClickListener(task: Task) {
        ItemsOnClick().onItemLongClick(task, activity, viewModel)
    }

    override fun onTaskCompliteListener(task: Task) {
        ItemsOnClick().onTaskComplite(task, viewModel)
    }

}
