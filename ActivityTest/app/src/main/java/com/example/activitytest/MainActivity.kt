package com.example.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Serializable传递对象
        val person = Person()
        person.name = "Tom"
        person.age = 20
//        val intent = Intent(this, SecondActivity::class.java)
//        intent.putExtra("person_data", person)
//        startActivity(intent)
//        在SecondActivity中用 getSerializableExtra("person_data") as Person 获取
//        在SecondActivity中用 getParcelableExtra("person_data") as Person 获取

    }
}