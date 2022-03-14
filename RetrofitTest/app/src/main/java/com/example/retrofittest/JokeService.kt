package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {

    @GET("getJoke")
    fun getJokeData(): Call<List<Joke>>

}