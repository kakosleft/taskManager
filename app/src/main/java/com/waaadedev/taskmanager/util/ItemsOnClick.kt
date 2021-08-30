package com.waaadedev.taskmanager.util

import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.viewModel.MainActivityViewModel

class ItemsOnClick {
    fun onTaskComplite(task: Task, viewModel: MainActivityViewModel) {
        task.isDone = !task.isDone
        viewModel.updateTask(task)
    }

    fun onItemLongClick(task: Task, activity: FragmentActivity, viewModel: MainActivityViewModel) {
        val dialog = MaterialDialog(activity)
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


}