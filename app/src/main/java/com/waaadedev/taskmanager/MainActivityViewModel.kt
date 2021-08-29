package com.waaadedev.taskmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.waaadedev.taskmanager.data.AppDatabase
import com.waaadedev.taskmanager.data.Task

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    var allTasks: MutableLiveData<List<Task>> = MutableLiveData()
    var currentTasks: MutableLiveData<List<Task>> = MutableLiveData()
    var completedTasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        val tasksDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        val list = tasksDao?.getAllTasks()
        allTasks.postValue(list)
    }

    private fun getCurrentTasks(){
        val tasksDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        val list = tasksDao?.getCurrentTasks()
        currentTasks.postValue(list)
    }

    private fun getCompletedTasks(){
        val tasksDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        val list = tasksDao?.getCompletedTasks()
        completedTasks.postValue(list)
    }

    fun getCurrentTasksObservers(): MutableLiveData<List<Task>>{
        return currentTasks
    }

    fun getCompletedTasksObservers(): MutableLiveData<List<Task>>{
        return completedTasks
    }

    fun getAllTasksObservers(): MutableLiveData<List<Task>> {
        return allTasks
    }

    fun insertTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.insert(task)
        getAllTasks()
        getCurrentTasks()
        getCompletedTasks()
    }

    fun updateTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.update(task)
        getAllTasks()
        getCurrentTasks()
        getCompletedTasks()
    }

    fun deleteTask(task: Task){
        val taskDao = AppDatabase.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.delete(task)
        getAllTasks()
        getCurrentTasks()
        getCompletedTasks()
    }
}