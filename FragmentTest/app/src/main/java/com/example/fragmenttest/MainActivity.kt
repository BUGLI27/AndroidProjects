package com.example.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager // 获取FragmentManager
//        val transaction = fragmentManager.beginTransaction() // 开启一个事务
//        transaction.replace(R.id.rightLayout, fragment) // 向容器内添加或替换Fragment
//        transaction.addToBackStack(null) // 将一个事务添加到返回栈中
//        transaction.commit() // 提交事务
    }
}