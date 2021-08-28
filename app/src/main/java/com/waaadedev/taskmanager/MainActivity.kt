package com.waaadedev.taskmanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import androidx.room.Room
import com.waaadedev.taskmanager.data.AppDatabase
import com.waaadedev.taskmanager.data.Task
import com.waaadedev.taskmanager.fragments.MainFragment
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.Notification
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var notification: Notification
    private lateinit var bottomBar: ExpandableBottomBar

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomBar()

        Data.db = createDatabase()

        test_data_save_and_read()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

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
                R.id.main_menu_home,
                R.drawable.house,
                R.string.all,
                ContextCompat.getColor(this, R.color.blue)
            ).build()
        )
        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.main_menu_shopping_cart,
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
        notification = bottomBar.menu.findItemById(R.id.main_menu_shopping_cart).notification()
        bottomBar.onItemSelectedListener = { _, menuItem, _ ->
            val fragmentContainerView =
                findViewById<FragmentContainerView>(R.id.main_fragments_view)
            fragmentContainerView.removeAllViews()
            if (menuItem.id == R.id.main_menu_settings) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, MainFragment())
                    .commit()
            } else if (menuItem.id == R.id.main_menu_home) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, MainFragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragments_view, MainFragment())
                    .commit()
            }
        }
    }

    private fun createDatabase(): AppDatabase{
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database"
            ).build()

    }

    private fun test_data_save_and_read(): Observable<Unit>{
        return Observable.create{
            val dao = Data.db.taskDao()
            var tasks = dao?.getAllTasks()
            val id: Int = tasks!!.size + 1
            val qwe = Task(
                id = id,
                description = "qwe",
                text = "qweqweqwe",
                date = "0303030303",
                isDone = false)
            dao?.insert(qwe)
            tasks = dao?.getAllTasks()
            Log.i("test data","1111 $tasks")
        }
    }

    object Data{
        lateinit var db: AppDatabase
    }
}