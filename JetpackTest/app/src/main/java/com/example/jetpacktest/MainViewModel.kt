package com.example.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {

//    var counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter
    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstname} ${user.lastName}"
    }

    private val userIdLiveData = MutableLiveData<String>()
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    // 无参数
    //private val refreshLiveData = MutableLiveData<Any?>()
    //val refreshResult = Transformations.switchMap(refreshLiveData) {
        //Repository.refresh()  // 假设Repository中已经定义了refresh()方法

    //}

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?:0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

}