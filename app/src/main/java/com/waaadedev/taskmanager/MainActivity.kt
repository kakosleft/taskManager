package com.waaadedev.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.waaadedev.taskmanager.adapters.TaskRecyclerAllAdapter
import com.waaadedev.taskmanager.adapters.TaskRecyclerCurrentAdapter
import com.waaadedev.taskmanager.fragments.CompletedTasksFragment
import com.waaadedev.taskmanager.fragments.CurrentTasksFragment
import com.waaadedev.taskmanager.fragments.MainFragment
import com.waaadedev.taskmanager.fragments.NewTaskFragment
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.Notification

class MainActivity : AppCompatActivity() {

    private lateinit var notification: Notification
    private lateinit var bottomBar: ExpandableBottomBar
    lateinit var floatingButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



        setBottomBar()
        floatingButton = findViewById(R.id.floatingActionButton)

        floatingButton.visibility = View.VISIBLE
        bottomBar.visibility = View.VISIBLE
        floatingButton.setOnClickListener {
            floatingButton.visibility = View.GONE
            bottomBar.visibility = View.GONE
            val fragmentContainerView =
                findViewById<FragmentContainerView>(R.id.main_fragments_view)
            fragmentContainerView.removeAllViews()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragments_view, NewTaskFragment())
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.main_fragments_view, MainFragment())
            .commit()
    }


    private fun setBottomBar() {
        bottomBar = findViewById(R.id.expandable_bottom_bar)
        val menu = bottomBar.menu
        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.all_tasks,
                R.drawable.house,
                R.string.all,
                ContextCompat.getColor(this, R.color.blue)
            ).build()
        )
        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.current_tasks,
                R.drawable.house,
                R.string.in_process,
                ContextCompat.getColor(this, R.color.red)
            ).build()
        )

        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.main_menu_settings,
                R.drawable.house,
                R.string.done,
                ContextCompat.getColor(this, R.color.green)
            ).build()
        )
//        notification = bottomBar.menu.findItemById(R.id.main_menu_shopping_cart).notification()
        bottomBar.onItemSelectedListener = { _, menuItem, _ ->
            val fragmentContainerView =
                findViewById<FragmentContainerView>(R.id.main_fragments_view)
            fragmentContainerView.removeAllViews()
            if (menuItem.id == R.id.all_tasks) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, MainFragment())
                    .commit()
            } else if (menuItem.id == R.id.current_tasks) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, CurrentTasksFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, CompletedTasksFragment())
                    .commit()
            }
        }
    }

}