package com.waaadedev.taskmanager.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.waaadedev.taskmanager.data.AppDatabase
import com.waaadedev.taskmanager.data.Task

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    var allTasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        val tasksDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        val list = tasksDao?.getAllTasks()
        allTasks.postValue(list)
    }

    fun getAllTasksObservers(): MutableLiveData<List<Task>> {
        return allTasks
    }

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