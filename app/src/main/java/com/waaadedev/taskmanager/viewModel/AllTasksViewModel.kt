package com.waaadedev.taskmanager.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.waaadedev.taskmanager.data.AppDatabase
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.fragments.AllTasksFragment

class AllTasksViewModel(app: Application): AndroidViewModel(app) {
    var currentTasks: MutableLiveData<List<Task>> = MutableLiveData()
    var doneTasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        currentTasks.postValue(AllTasksFragment.t.currentTasks)
        doneTasks.postValue(AllTasksFragment.t.doneTasks)
    }
//
//    fun getAllTasksObservers(): MutableLiveData<List<Task>> {
//        return allTasks
//    }

    fun insertTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.insert(task)
        getAllTasks()
    }

    fun updateTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.update(task)
        getAllTasks()
    }

    fun deleteTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.delete(task)
        getAllTasks()
    }
}