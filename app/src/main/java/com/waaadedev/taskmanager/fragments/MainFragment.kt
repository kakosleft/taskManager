package com.waaadedev.taskmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waaadedev.taskmanager.R
import com.waaadedev.taskmanager.adapters.TaskRecyclerAdapter

class MainFragment : Fragment() {

    private lateinit var taskRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        createRecyclerView(view)


        return view
    }

    private fun createRecyclerView(view: View) {
        taskRecyclerView = view.findViewById(R.id.main_fragment_recycler_view)
        taskRecyclerView.adapter = TaskRecyclerAdapter()
    }


}