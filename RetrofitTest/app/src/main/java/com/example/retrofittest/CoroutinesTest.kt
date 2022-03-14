package com.example.retrofittest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    GlobalScope.launch {
//        println("codes run in coroutine scope")
//    }
//    Thread.sleep(1000)
    runBlocking {
        launch {
            println("launch1")
            delay(1500)
            println("launch1 finished")
        }
        launch {
            println("launch2")
            delay(1500)
            println("launch2 finished")
        }
    }
}