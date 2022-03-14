package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getAppDataBtn = findViewById<Button>(R.id.getAppDataBtn)
        getAppDataBtn.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.apiopen.top/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(JokeService::class.java)
            appService.getJokeData().enqueue(object : Callback<List<Joke>> {
                override fun onResponse(call: Call<List<Joke>>, response: Response<List<Joke>>) {
                    val list = response.body()
                    if (list != null) {
                        for (joke in list) {
                            Log.d("MainActivity", "title is ${joke.title}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Joke>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

    }
}