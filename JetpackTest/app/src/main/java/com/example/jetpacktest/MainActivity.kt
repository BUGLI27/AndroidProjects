package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // lifeCycleOwner 感知生命周期
        lifecycle.addObserver(MyObserver(this.lifecycle))
        // 数据保存
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0) // 如果没有则为默认值0
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)

        val plusOneBtn = findViewById<Button>(R.id.plusOneBtn)
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this) { count ->
            val infoText = findViewById<TextView>(R.id.infoText)
            infoText.text = count.toString()
        }
        // switchMap
        val getUserBtn = findViewById<Button>(R.id.getUserBtn)
        getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this, Observer { user ->
            val infoText = findViewById<TextView>(R.id.infoText)
            infoText.text = user.firstname
        })
        // Room
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        val addDataBtn = findViewById<Button>(R.id.addDataBtn)
        val updateDataBtn = findViewById<Button>(R.id.updateDataBtn)
        val deleteDataBtn = findViewById<Button>(R.id.deleteDataBtn)
        val queryDataBtn = findViewById<Button>(R.id.queryDataBtn)
        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }
        // WorkManager
        val doWorkBtn = findViewById<Button>(R.id.doWorkBtn)
        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag("simple")
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)
            // 对后台任务结果进行监听
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request.id)
                .observe(this) { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.d("MainActivity", "do work succeeded")
                    } else if (workInfo.state == WorkInfo.State.FAILED) {
                        android.util.Log.d("MainActivity", "do work failed")
                    }
                }
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit() {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}